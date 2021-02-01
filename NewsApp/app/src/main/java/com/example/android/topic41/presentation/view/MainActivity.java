package com.example.android.topic41.presentation.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.android.topic41.BaseApplication;
import com.example.android.topic41.R;


import com.example.android.topic41.di.components.DaggerMainActivityComponent;
import com.example.android.topic41.di.components.MainActivityComponent;
import com.example.android.topic41.domain.util.Theme;
import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.presentation.view.adapter.Adapter;
import com.example.android.topic41.presentation.viewmodel.ArticlesViewModel;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Article> articles = new ArrayList<>();;
    private Adapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private String theme;

    @Inject
    ArticlesViewModel model;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activity(this)
                .build();
        component.inject(this);

        initSpinner(R.id.spinner_category, new Theme());

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(articles);
        recyclerView.setAdapter(adapter);

        refreshLayout =  findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> model.loadArticles(theme));

        model.getIsLoadingState().observe(MainActivity.this, loadingState -> {
            if (loadingState) {
                refreshLayout.setRefreshing(true);
                recyclerView.setVisibility(View.GONE);
            } else {
                refreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.VISIBLE);
                if (!model.getIsShownErrorMessage().getValue()) {
                    Toast.makeText(this, model.getErrorMessage().getValue(), Toast.LENGTH_LONG).show();
                    model.errorMessageShowed();
                }
            }
        });
        model.getArticles().observe(MainActivity.this, list -> {
            adapter.setArticles(list);
            Log.i("mLog_ACTIVITY", String.valueOf(list.size()));
            for (Article article : list) {
                Log.i("mLog_ACTIVITY", article.getTheme() + " // " + article.getTitle());
            }

        });
    }

    @Override
    protected void onDestroy() {
        Log.i("mLog_ACTIVITY", "activityDestroyed");
        model.activityDestroyed();
        super.onDestroy();
    }

    private Spinner initSpinner(int id, @NonNull Theme data) {
        Spinner spinner = findViewById(id);
        ArrayAdapter<String> adapterForBandSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data.getRequestParameterKeys());
        adapterForBandSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterForBandSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view != null) {
                    String key = parent.getSelectedItem().toString();
                    theme = data.getRequestParameterValues().get(key);
                    model.loadArticles(theme);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return spinner;
    }
}
