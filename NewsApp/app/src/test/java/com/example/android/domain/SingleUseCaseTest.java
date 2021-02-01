package com.example.android.domain;


import com.example.android.topic41.domain.usecase.SingleUseCase;
import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.domain.util.Source;
import com.example.android.topic41.data.repository.ArticlesRepositoryInterface;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class SingleUseCaseTest {

    @Mock
    private ArticlesRepositoryInterface repository;

    private SingleUseCase useCase;

    private List<Article> testList;
    private String testTheme = "theme";

    @Before
    public void singleUseCaseTestBefore() {
        MockitoAnnotations.openMocks(this);
        useCase = new SingleUseCase(repository);

        initTestList();
        final Single<List<Article>> observableTestList = Single.just(testList);
        when(repository.loadArticles(testTheme)).thenReturn(observableTestList);
    }

    @Test
    public void loadArticlesTest() {
        List<Article> result = useCase.loadArticles(testTheme).blockingGet();
        for (Article article : result) {
            if (article.getTitle().length() > 20) {
                assertTrue(article.getTitle().contains("Filtered"));
            } else {
                assertFalse(article.getTitle().contains("Filtered"));
            }
        }
    }


    public void initTestList() {
        testList = new ArrayList<>();
        Article article1 = new Article(new Source(null, "1"), "1", "01234567890123456789", "1","1","1","1","1");
        Article article2 = new Article(new Source(null, "2"), "2", "2", "2","2","2","2","2");
        Article article3 = new Article(new Source(null, "3"), "3", "3", "3","3","3","3","3");
        article1.setTheme(testTheme);
        article2.setTheme(testTheme);
        article3.setTheme(testTheme);
    }
}
