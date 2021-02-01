package com.example.android.topic41.presentation.view.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.topic41.R;
import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.presentation.view.ArticleActivity;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Article> articles;
    private static final String SELECTED_ARTICLE = "selectedArticle";

    public Adapter(@NonNull List<Article> articles) {
        this.articles = articles;
    }

    public void setArticles(@NonNull List<Article> articles) {
        this.articles = articles;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.articles_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView author;
        private TextView title;
        private TextView publishedAt;
        private Article article;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author_item);
            title = itemView.findViewById(R.id.title_item);
            publishedAt = itemView.findViewById(R.id.publishedAt_item);

            itemView.setOnClickListener( (v) -> {
                Intent intent = new Intent(v.getContext(), ArticleActivity.class);
                intent.putExtra(SELECTED_ARTICLE, article);
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation((Activity) itemView.getContext(), v,
                                v.getContext().getResources().getString(R.string.transition_to_article));
                v.getContext().startActivity(intent, options.toBundle());
            });
        }

        public void bind(@NonNull Article article) {
            this.article = article;
            this.author.setText(article.getAuthor());
            this.title.setText(article.getTitle());
            this.publishedAt.setText(article.getPublishedAt());
        }
    }
}
