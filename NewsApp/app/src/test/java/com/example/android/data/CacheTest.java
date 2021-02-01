package com.example.android.data;

import android.util.Log;

import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.domain.util.Source;
import com.example.android.topic41.data.database.ArticleDao;
import com.example.android.topic41.data.database.ArticlesDatabase;
import com.example.android.topic41.data.database.Cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CacheTest {

    @Mock
    private ArticlesDatabase database;

    @Mock
    private ArticleDao articleDao;

    private Cache cache;

    private List<Article> testList;
    private String testTheme = "theme";

    @Before
    public void cacheTestBefore() {
        MockitoAnnotations.openMocks(this);
        cache = Cache.getInstance(database, articleDao);

        testList = initTestList();
        final Single<List<Article>> observableTestList = Single.just(testList);
        when(articleDao.getArticlesByTheme(testTheme)).thenReturn(observableTestList);
    }

    @Test
    public void refreshAndGetArticlesByThemeTest(){
        long currentTime = System.currentTimeMillis();
        cache.refresh(testList, currentTime);
        verify(articleDao).deleteByTheme(testTheme, currentTime);
        verify(articleDao).insert(testList);

        List<Article> result = cache.getArticlesByTheme(testTheme).blockingGet();
        assertEquals(testList, result);
        verify(articleDao).getArticlesByTheme(testTheme);
    }

    @After
    public void after() {
        database.close();
    }

    public List<Article> initTestList() {
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
        return list;
    }
}
