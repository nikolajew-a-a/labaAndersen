package com.example.android.data;


import com.example.android.topic41.data.network.JsonPlaceHolderApi;
import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.data.network.Network;
import com.example.android.topic41.domain.util.News;
import com.example.android.topic41.domain.util.Source;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NetworkTest {

    @Mock
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Network network;

    private News testNews;
    private String testTheme = "theme";


    @Before
    public void NetworkTestBefore() {
        MockitoAnnotations.openMocks(this);
        network = new Network(jsonPlaceHolderApi);

        testNews = initNews();
        Single<News> observableNews = Single.just(testNews);
        when(jsonPlaceHolderApi.getNews(any())).thenReturn(observableNews);
    }


    @Test
    public void createObservableTest() {
        List<Article> result = network.createObservable(testTheme).blockingGet();

        verify(jsonPlaceHolderApi).getNews(any());
        assertEquals(testNews.getArticles(), result);
    }

    public News initNews() {
        News news = new News();
        List<Article> list = new ArrayList<>();
        Article article1 = new Article(new Source(null, "1"), "1", "1", "1","1","1","1","1");
        Article article2 = new Article(new Source(null, "2"), "2", "2", "2","2","2","2","2");
        Article article3 = new Article(new Source(null, "3"), "3", "3", "3","3","3","3","3");
        article1.setTheme(testTheme);
        article2.setTheme(testTheme);
        article3.setTheme(testTheme);
        list.add(article1);
        list.add(article2);
        list.add(article3);

        news.setArticles(list);
        return news;
    }
}
