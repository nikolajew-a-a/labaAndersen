package com.example.android.topic41.presentation.view.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.topic41.R
import com.example.android.topic41.domain.util.Article
import com.example.android.topic41.presentation.view.ArticleActivity

class Adapter(private var articles: List<Article>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    fun setArticles(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.articles_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val author: TextView = itemView.findViewById(R.id.author_item)
        private val title: TextView = itemView.findViewById(R.id.title_item)
        private val publishedAt: TextView = itemView.findViewById(R.id.publishedAt_item)
        private lateinit var article: Article

        fun bind(article: Article) {
            this.article = article
            author.text = article.author
            title.text = article.title
            publishedAt.text = article.publishedAt
        }

        init {
            itemView.setOnClickListener { v: View ->
                val intent = Intent(v.context, ArticleActivity::class.java)
                intent.putExtra(SELECTED_ARTICLE, article)
                val options = ActivityOptions
                        .makeSceneTransitionAnimation(itemView.context as Activity, v,
                                v.context.resources.getString(R.string.transition_to_article))
                v.context.startActivity(intent, options.toBundle())
            }
        }
    }

    companion object {
        private const val SELECTED_ARTICLE = "selectedArticle"
    }
}
