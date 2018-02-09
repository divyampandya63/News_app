package com.example.divyampandya63.news_app.Common;

import com.example.divyampandya63.news_app.Interface.IconBetterIdeaService;
import com.example.divyampandya63.news_app.Interface.NewsService;
import com.example.divyampandya63.news_app.Remote.IconBetterIdeaClient;
import com.example.divyampandya63.news_app.Remote.RetrofitClient;

import retrofit2.Retrofit;

import static com.example.divyampandya63.news_app.Remote.RetrofitClient.getclient;

/**
 * Created by divyampandya63 on 18/12/17.
 */

public class Common {

    private static final String BASE_URL = "https://newsapi.org/";

    public static final String API_KEY = "aeb3a90e400442b5a57644f770d9bbc5";

    public static NewsService getNewsService() {

        return RetrofitClient.getclient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService() {

        return IconBetterIdeaClient.getclient().create(IconBetterIdeaService.class);
    }

    //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=aeb3a90e400442b5a57644f770d9bbc5

    public static String getAPIUrl(String source,String apiKEY){
        StringBuilder apiUrl= new StringBuilder("https://newsapi.org/v2/top-headlines?sources=")
                .append(source);
        return apiUrl.append("&apiKey=")
                .append(apiKEY).toString();
    }

}

