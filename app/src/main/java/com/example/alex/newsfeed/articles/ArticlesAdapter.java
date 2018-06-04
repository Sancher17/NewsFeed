package com.example.alex.newsfeed.articles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.newsfeed.R;
import com.example.alex.newsfeed.retrofit.NewsItem;
import com.example.alex.newsfeed.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    @BindView(R.id.head)
    TextView name;

    @BindView(R.id.date)
    TextView date;
    
    private String TAG = "ArticlesAdapter";

    private ArrayList<NewsItem> list = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @NonNull
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate
                (R.layout.card_view, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        ButterKnife.bind(this, cardView);

        name.setText(list.get(position).getHeadLine());
        date.setText(list.get(position).getDateLine());

        // TODO: 29.05.2018 click - реакция
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + position);
                EventBus.getDefault().post(new MessageEvent(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNewsItems(ArrayList <NewsItem> list){
        this.list = list;
    }

    public void clearAdapter(){
        list.clear();
    }

    public void setNewsItem(NewsItem item){
        list.add(item);
    }

}
