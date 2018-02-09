package com.example.divyampandya63.news_app;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.divyampandya63.news_app.Adapter.ListSourceAdapter;
import com.example.divyampandya63.news_app.Common.Common;
import com.example.divyampandya63.news_app.Interface.NewsService;
import com.example.divyampandya63.news_app.Model.Website;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    SpotsDialog dialog;
    SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init cache
        Paper.init(this);

        //Init service
        mService= Common.getNewsService();

        //Init view
        swipeLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });


        listWebsite=(RecyclerView)findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);

        dialog=new SpotsDialog(this);

        loadWebsiteSource(false);

    }

    private void loadWebsiteSource(boolean isRefreshed) {

        if(!isRefreshed){
            String cache=Paper.book().read("cache");
            //if there is a cache
            if(cache!=null && !cache.isEmpty()){
                Website website=new Gson().fromJson(cache,Website.class);//Convert cache from Json to object
                adapter=new ListSourceAdapter(getBaseContext(),website);
                adapter.notifyDataSetChanged();
                listWebsite.setAdapter(adapter);
            }
            else   //if not have cache
            {
              dialog.show();
              //Fetch new data
                mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {

                        adapter =new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebsite.setAdapter(adapter);


                        //save to cache
                        Paper.book().write("cache",new Gson().toJson(response.body()));
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                    }
                });
            }

        }
        else  //if swipe to refresh
            {
                dialog.show();
                //Fetch new data
                mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {

                        adapter =new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebsite.setAdapter(adapter);


                        //save to cache
                        Paper.book().write("cache",new Gson().toJson(response.body()));

                        //Dismiss refresh processing
                        swipeLayout.setRefreshing(false);
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                    }
                });
            }

    }
}
