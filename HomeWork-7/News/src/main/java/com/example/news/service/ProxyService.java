package com.example.news.service;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    @Value("${news.apiKey}")
    private String apiKey;
    private final NewsApiClient newsApiClient = new NewsApiClient(apiKey);
    private String[] articles;

    public void getNews() {
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q("ukraine")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        int size = response.getArticles().size();
                        articles = new String[size];
                        for (int i = 0; i < size; i++) {
                            articles[i] = ("!!!Glory to Ukraine!!! " + response.getArticles().get(i).getTitle());
                        }

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }

    public String[] provideCustomNews() {
        getNews();
        return articles;
    }
}
