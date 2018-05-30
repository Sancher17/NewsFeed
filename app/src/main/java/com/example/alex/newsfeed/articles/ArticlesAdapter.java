package com.example.alex.newsfeed.articles;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.newsfeed.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.action)
    TextView action;
    
    String TAG = "ArticlesAdapter";

    private ArrayList<String> items = new ArrayList<>();

    public ArticlesAdapter(ArrayList<String> list) {
        items = list;
    }

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
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, int position) {

        CardView cardView = holder.cardView;
        ButterKnife.bind(this, cardView);

        name.setText(items.get(position));
        action.setText("action");


        // TODO: 29.05.2018 click - реакция
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
