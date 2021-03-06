package com.example.divyampandya63.news_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.divyampandya63.news_app.Common.Common;
import com.example.divyampandya63.news_app.Interface.IconBetterIdeaService;
import com.example.divyampandya63.news_app.Interface.ItemClickListener;
import com.example.divyampandya63.news_app.ListNews;
import com.example.divyampandya63.news_app.MainActivity;
import com.example.divyampandya63.news_app.Model.IconBetterIdea;
import com.example.divyampandya63.news_app.Model.Website;
import com.example.divyampandya63.news_app.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by divyampandya63 on 18/12/17.
 */

class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener;
    TextView source_title;
    CircleImageView source_image;

    public ListSourceViewHolder(View itemView){

        super(itemView);

        source_image=(CircleImageView)itemView.findViewById(R.id.source_image);
        source_title=(TextView)itemView.findViewById(R.id.source_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder> {

    private Context context;
    private Website website;

    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context, Website website) {

        this.website = website;
        this.context = context;

        mService= Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.source_layout,parent,false);

        return new ListSourceViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {

        StringBuilder iconBetterAPI=new StringBuilder("https://icons.better-idea.org/allicons.json?url=");
        iconBetterAPI.append(website.getSources().get(position).getUrl());

        mService.getIconUrl(iconBetterAPI.toString())
                .enqueue(new Callback<IconBetterIdea>() {
            @Override
            public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {
               if(response.body().getIcons().size()>0){
                   Picasso.with(context)
                           .load(response.body().getIcons().get(0).getUrl())
                           .into(holder.source_image);

               }
            }

            @Override
            public void onFailure(Call<IconBetterIdea> call, Throwable t) {

            }
        });

        holder.source_title.setText(website.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent =new Intent(context, ListNews.class);
                intent.putExtra("source",website.getSources().get(position).getId());
                intent.putExtra("sortBy",website.getSources().get(position).getSortBysAvailable().get(0)); //get Default sortBy Method
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return website.getSources().size();
    }
}
