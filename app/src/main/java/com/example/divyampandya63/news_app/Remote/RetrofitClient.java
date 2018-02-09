package com.example.divyampandya63.news_app.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by divyampandya63 on 18/12/17.
 */

public class RetrofitClient {
    private static Retrofit retrofit =null;
    public static Retrofit getclient(String baseUrl){

        if (retrofit==null){
        retrofit =   new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
