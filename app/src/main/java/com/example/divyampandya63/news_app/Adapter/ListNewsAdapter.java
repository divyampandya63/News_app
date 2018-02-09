package com.example.divyampandya63.news_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.divyampandya63.news_app.Common.ISO8601Parse;
import com.example.divyampandya63.news_app.DetailArticle;
import com.example.divyampandya63.news_app.Interface.ItemClickListener;
//import com.example.divyampandya63.news_app.Model.Article;
import com.example.divyampandya63.news_app.Model.Article;
import com.example.divyampandya63.news_app.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by divyampandya63 on 19/12/17.
 */

class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener;
    TextView article_title;
    RelativeTimeTextView article_time;
    CircleImageView article_image;


    public ListNewsViewHolder(View itemView) {
        super(itemView);
        article_image=(CircleImageView)itemView.findViewById(R.id.article_image);
        article_title=(TextView) itemView.findViewById(R.id.article_title);
        article_time=(RelativeTimeTextView)itemView.findViewById(R.id.article_time);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setArticle_title(TextView article_title) {
        this.article_title = article_title;
    }

    public void setArticle_time(RelativeTimeTextView article_time) {
        this.article_time = article_time;
    }

    public void setArticle_image(CircleImageView article_image) {
        this.article_image = article_image;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {

    private List<Article> articleList;

    private Context context;

    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;

    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.news_layoyut,parent,false);
        return new ListNewsViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        Picasso.with(context)
                .load( articleList.get(position).getUrlToImage())
                .into(holder.article_image);

        if(articleList.get(position).getTitle().length() >65){
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"...");
        }
        else
            holder.article_title.setText(articleList.get(position).getTitle());

        Date date=null;
        try {
            date= ISO8601Parse.parse(articleList.get(position).getPublishedAt());
        }
        catch (ParseException ex){
            ex.printStackTrace();
        }
        holder.article_time.setReferenceTime(date.getTime());

        //Set event click
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent detail=new Intent(context,DetailArticle.class);
                detail.putExtra("webURL",  articleList.get(position).getUrl());
                context.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
