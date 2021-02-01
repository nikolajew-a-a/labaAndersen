package com.example.android.topic41.data.repository;

import com.example.android.topic41.data.database.CacheInterface;
import com.example.android.topic41.data.network.NetworkInterface;
import com.example.android.topic41.domain.util.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class ArticlesRepository implements ArticlesRepositoryInterface {
    private NetworkInterface network;
    private CacheInterface cache;
    private Map<String, Long> cacheTime = new HashMap<>();

    private static final int TIME_TO_REFRESH = 60_000;


    public ArticlesRepository(CacheInterface cache, NetworkInterface network) {
        this.cache = cache;
        this.network = network;
    }


    @Override
    public Single<List<Article>> loadArticles(String theme) {
        long currentTime = System.currentTimeMillis();
        boolean isCacheFull = cacheTime.get(theme) != null && currentTime - cacheTime.get(theme) < TIME_TO_REFRESH;
        if (isCacheFull) {
            return cache.getArticlesByTheme(theme);
        } else {
            return network.createObservable(theme)
                    .map(articles -> prepareForCache(theme, articles))
                    .map(this::refreshCache);
        }
    }


    public List<Article> prepareForCache(String theme, List<Article> articles) {
        long currentTime = System.currentTimeMillis();
        cacheTime.put(theme, currentTime);
        for (Article article : articles) {
            article.setTime(currentTime);
            article.setTheme(theme);
        }
        return articles;
    }

    public List<Article> refreshCache(List<Article> articles) {
        if (articles.size() > 0) {
            cache.refresh(articles, articles.get(0).getTime());
        }
        return articles;
    }
}
