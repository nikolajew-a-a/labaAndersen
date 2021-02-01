package com.example.android.data;

import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.domain.util.Source;
import com.example.android.topic41.data.database.CacheInterface;
import com.example.android.topic41.data.network.NetworkInterface;
import com.example.android.topic41.data.repository.ArticlesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    @Mock
    private NetworkInterface network;

    @Mock
    private CacheInterface cache;

    private ArticlesRepository repository;
    private List<Article> testList;
    private String testTheme = "theme";

    @Before
    public void repositoryTestBefore() {
        MockitoAnnotations.openMocks(this);
        repository = new ArticlesRepository(cache, network);

        initTestList();
        final Single<List<Article>> observableTestList = Single.just(testList);
        when(network.createObservable(testTheme)).thenReturn(observableTestList);
        when(cache.getArticlesByTheme(testTheme)).thenReturn(observableTestList);
    }

    @Test
    public void loadArticlesTest() {
        repository.loadArticles(testTheme).blockingGet();
        verify(cache, times(0)).getArticlesByTheme(testTheme);
        verify(network, times(1)).createObservable(testTheme);

        for (int i = 0; i < 10; i++) {
            repository.loadArticles(testTheme).blockingGet();
            verify(cache, times(i + 1)).getArticlesByTheme(testTheme);
            verify(network, times(1)).createObservable(testTheme);
        }
    }


    public void initTestList() {
        testList = new ArrayList<>();
        Article article1 = new Article(new Source(null, "1"), "1", "1", "1","1","1","1","1");
        Article article2 = new Article(new Source(null, "2"), "2", "2", "2","2","2","2","2");
        Article article3 = new Article(new Source(null, "3"), "3", "3", "3","3","3","3","3");
        article1.setTheme("theme");
        article2.setTheme("theme");
        article3.setTheme("theme");
        testList.add(article1);
        testList.add(article2);
        testList.add(article3);
    }
}
