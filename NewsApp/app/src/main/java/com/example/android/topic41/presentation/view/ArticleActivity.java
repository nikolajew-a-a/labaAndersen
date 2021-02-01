package com.example.android.topic41.presentation.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.android.topic41.R;
import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.databinding.ActivityArticleBinding;


public class ArticleActivity extends AppCompatActivity {
    private ActivityArticleBinding mBinding;

    private static final String SELECTED_ARTICLE = "selectedArticle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Article article = (Article) intent.getParcelableExtra(SELECTED_ARTICLE);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);
        mBinding.setArticle(article);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
