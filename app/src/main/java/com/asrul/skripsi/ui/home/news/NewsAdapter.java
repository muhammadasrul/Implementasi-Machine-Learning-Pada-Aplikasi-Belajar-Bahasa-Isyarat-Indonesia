package com.asrul.skripsi.ui.home.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.data.News;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;

    public void setNews(List<News> list) {
        if (newsList == null) {
            newsList = list;
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        Context context = holder.itemView.getContext();

        holder.tvNewsTitle.setText(news.getTitle());
        holder.tvNewsDesc.setText(news.getContent());
        holder.tvNewsWriter.setText(String.format("oleh: %s", news.getWriter()));
        Glide.with(context)
                .load(news.getImgUrl())
                .into(holder.imgNews);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, NewsWebViewActivity.class);
            intent.putExtra(NewsWebViewActivity.URL_EXTRA, news.getSource());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgNews;
        TextView tvNewsTitle, tvNewsDesc, tvNewsWriter;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.imgNews);
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvNewsDesc = itemView.findViewById(R.id.tvNewsDesc);
            tvNewsWriter = itemView.findViewById(R.id.tvNewsWriter);
        }
    }
}
