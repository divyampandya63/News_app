package com.example.divyampandya63.news_app.Interface;

import com.example.divyampandya63.news_app.Model.IconBetterIdea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by divyampandya63 on 18/12/17.
 */

public interface IconBetterIdeaService {

    @GET
    Call<IconBetterIdea>getIconUrl(@Url String url);
}
