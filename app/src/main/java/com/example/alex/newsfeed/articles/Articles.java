package com.example.alex.newsfeed.articles;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.alex.newsfeed.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Articles extends AppCompatActivity  implements ArticlesContract.View {

    @BindView(R.id.swiperefreshRecycler)
    SwipeRefreshLayout swipe;

    private ArrayList<String> list = new ArrayList<>();

    private ArticlesAdapter adapter = new ArticlesAdapter(list);

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();
        iniList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(adapter);

        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Toast.makeText(Articles.this, "onRefresh", Toast.LENGTH_SHORT).show();

                        list.add("New String");
                        swipe.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }
        );
    }

    private void iniList() {
        list.add("Alex");
        list.add("Cool");
        list.add("Boy");
    }
}
