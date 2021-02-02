package com.example.android.topic41.presentation.view


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.android.topic41.BaseApplication
import com.example.android.topic41.R
import com.example.android.topic41.di.components.DaggerMainActivityComponent
import com.example.android.topic41.domain.util.Article
import com.example.android.topic41.domain.util.Theme
import com.example.android.topic41.presentation.view.adapter.Adapter
import com.example.android.topic41.presentation.viewmodel.ArticlesViewModel
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val articles: List<Article> = ArrayList()
    private lateinit var adapter: Adapter
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var theme: String

    @Inject
    lateinit var model: ArticlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerMainActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activity(this)
                .build()
        component.inject(this)

        initSpinner(R.id.spinner_category, Theme())
        recyclerView = findViewById(R.id.recyclerview)
        adapter = Adapter(articles)
        recyclerView.adapter = adapter
        refreshLayout = findViewById(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener { model.loadArticles(theme) }

        model.isLoadingState.observe(this) { loadingState: Boolean ->
            when (loadingState) {
                true -> { refreshLayout.isRefreshing = true
                    recyclerView.visibility = View.GONE
                }
                false -> { refreshLayout.isRefreshing = false
                    recyclerView.visibility = View.VISIBLE
                    if (!model.isShownErrorMessage.value!!) {
                        Toast.makeText(this, model.errorMessage.value, Toast.LENGTH_LONG).show()
                        model.errorMessageShowed()
                    }
                }
            }
        }

        model.articles.observe(this) { list: List<Article> ->
            adapter.setArticles(list)
        }
    }


    override fun onDestroy() {
        model.activityDestroyed()
        super.onDestroy()
    }


    private fun initSpinner(id: Int, data: Theme): Spinner {
        val spinner = findViewById<Spinner>(id)
        val adapterForBandSpinner = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, data.requestParameterKeys)
        adapterForBandSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterForBandSpinner
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val key = parent.selectedItem.toString()
                theme = data.requestParameterValues[key]!!
                model.loadArticles(theme)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        return spinner
    }
}
