package com.example.divyampandya63.news_app.Interface;

import com.example.divyampandya63.news_app.Model.News;
import com.example.divyampandya63.news_app.Model.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by divyampandya63 on 18/12/17.
 */

public interface NewsService {
    @GET("v1/sources?Language=en")
    Call<Website> getSources();

    @GET("")
    Call<News> getNewestArticles(@Url String url);

}
