package com.example.alex.newsfeed;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swiperefreshRecycler)
    SwipeRefreshLayout swipe;

    private ArrayList<String> list = new ArrayList<>();

    private RecyclerAdapter adapter = new RecyclerAdapter(list);

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(adapter);

        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Toast.makeText(MainActivity.this, "onRefresh", Toast.LENGTH_SHORT).show();

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
