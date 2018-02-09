package com.example.divyampandya63.news_app.Model;

import java.util.List;

/**
 * Created by divyampandya63 on 19/12/17.
 */

public class Article {

    private String author,title,description,url,urlToImage,publishedAt;

    public List<Sources> getList() {
        return list;
    }

    public void setList(List<Sources> list) {
        this.list = list;
    }

    private List<Sources> list;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
