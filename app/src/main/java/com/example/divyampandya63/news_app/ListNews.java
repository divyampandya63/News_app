package com.example.divyampandya63.news_app;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.divyampandya63.news_app.Adapter.ListNewsAdapter;
import com.example.divyampandya63.news_app.Common.Common;
import com.example.divyampandya63.news_app.Interface.NewsService;
import com.example.divyampandya63.news_app.Model.Article;
import com.example.divyampandya63.news_app.Model.News;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNews extends AppCompatActivity {

    KenBurnsView kbv;
    SpotsDialog dialog;
    DiagonalLayout diagonalLayout;
    NewsService newsService;
    TextView top_author, top_title;
    SwipeRefreshLayout swipeRefreshLayout;


    String source = "";
    String sortBy = "";
    String webHotURL = "";
    ListNewsAdapter adapter;
    RecyclerView listNews;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        //Service
        newsService = Common.getNewsService();

        dialog = new SpotsDialog(this);

        //View
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               loadNews(source, true);


            }
        });

        diagonalLayout = (DiagonalLayout) findViewById(R.id.diagonalLayout);
        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(getBaseContext(), DetailArticle.class);
                detail.putExtra("webURL", webHotURL);
                startActivity(detail);
            }
        });

        kbv = (KenBurnsView) findViewById(R.id.top_image);
        top_author = (TextView) findViewById(R.id.top_author);
        top_title = (TextView) findViewById(R.id.top_title);

        listNews = (RecyclerView) findViewById(R.id.listNews);
        listNews.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listNews.setLayoutManager(layoutManager);

        //Intent
        if (getIntent() != null) {
            source = getIntent().getStringExtra("source");
            sortBy = getIntent().getStringExtra("sortBy");
            if (!source.isEmpty() && !sortBy.isEmpty()) {
                loadNews(source, false);

            }
        }
    }

    private void loadNews(String source, boolean isRefreshed) {
        if (!isRefreshed) {
            dialog.show();
            newsService.getNewestArticles(Common.getAPIUrl(source, Common.API_KEY))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            dialog.dismiss();
                            Log.d("divyam", "" + response.body().getArticles().get(0));
                            //Get first article
                            Picasso.with(getBaseContext())
                                    .load(response.body().getArticles().get(0).getUrlToImage())
                                    .into(kbv);

                            top_title.setText(response.body().getArticles().get(0).getTitle());
                            top_author.setText(response.body().getArticles().get(0).getAuthor());

                            webHotURL = response.body().getArticles().get(0).getUrl();

                            //load remaining articles
                            List<Article> removeFirstItem = response.body().getArticles();
                            //because we already load first item to show on diagonal element
                            //so we need to remove it
                            removeFirstItem.remove(0);
                            adapter = new ListNewsAdapter(removeFirstItem, getBaseContext());
                            adapter.notifyDataSetChanged();
                            listNews.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
        } else {
            dialog.show();
            newsService.getNewestArticles(Common.getAPIUrl(source, Common.API_KEY))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            dialog.dismiss();
                            Log.d("divyam", "" + response.body().getArticles().get(0));
                            //Get first article
                            Picasso.with(getBaseContext())
                                    .load(response.body().getArticles().get(0).getUrlToImage())
                                    .into(kbv);

                            top_title.setText(response.body().getArticles().get(0).getTitle());
                            top_author.setText(response.body().getArticles().get(0).getAuthor());

                            webHotURL = response.body().getArticles().get(0).getUrl();

                            //load remaining articles
                            List<Article> removeFirstItem = response.body().getArticles();
                            //because we already load first item to show on diagonal element
                            //so we need to remove it
                            removeFirstItem.remove(0);
                            adapter = new ListNewsAdapter(removeFirstItem, getBaseContext());
                            adapter.notifyDataSetChanged();
                            listNews.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
            swipeRefreshLayout.setRefreshing(false);

        }
    }
}







