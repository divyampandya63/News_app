package com.example.divyampandya63.news_app.Model;

import java.util.List;

/**
 * Created by divyampandya63 on 19/12/17.
 */

public class News {
    private String status;
    private String totalResults;

    private List<Article> articles;

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}