package com.example.divyampandya63.news_app.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by divyampandya63 on 18/12/17.
 */

public class IconBetterIdeaClient {

    private static Retrofit retrofit =null;
    public static Retrofit getclient(){

        if (retrofit==null){
            retrofit =new Retrofit.Builder()
                    .baseUrl("https://icons.better-idea.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
