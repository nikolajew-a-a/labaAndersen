package com.example.android.topic41.presentation.view

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.topic41.R
import com.example.android.topic41.databinding.ActivityArticleBinding
import com.example.android.topic41.domain.util.Article


class ArticleActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article)
        mBinding.article = intent.getParcelableExtra<Parcelable>(SELECTED_ARTICLE) as Article
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val SELECTED_ARTICLE = "selectedArticle"
    }
}
