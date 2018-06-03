package com.example.alex.newsfeed.articles;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.alex.newsfeed.BuildConfig;
import com.example.alex.newsfeed.R;
import com.example.alex.newsfeed.dagger.AppInject;
import com.example.alex.newsfeed.data.Articles;
import com.example.alex.newsfeed.retrofit.Example;
import com.example.alex.newsfeed.retrofit.IndiaApi;
import com.example.alex.newsfeed.retrofit.NewsItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesActivity extends AppCompatActivity implements ArticlesContract.View {


    @BindView(R.id.swiperefreshRecycler)
    SwipeRefreshLayout swipe;

    @Inject
    ArticlesAdapter adapter;

    @Inject
    ArticlesContract.Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);
        AppInject.getComponent().inject(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(adapter);

        presenter.attachView(this);
        presenter.loadArticles();

        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Toast.makeText(ArticlesActivity.this, "onRefresh", Toast.LENGTH_SHORT).show();
                        presenter.loadArticles();

                    }
                }
        );


    }

    void printAll() {
//        for (NewsItem list: listItems) {
//            Log.d(TAG, "items: " + list.getHeadLine());
//        }
//        for (int i = 0; i < 3; i++) {
//            Log.d(TAG, "items: " + listItems.get(i).getStory());
//        }

//        adapter.items.add(listItems.get(1).getHeadLine());
//        adapter.notifyDataSetChanged();
    }



    @Override
    public void showArticles(List<NewsItem> articles) {

    }

    @Override
    public void showArticlesEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void hideProgressBar() {
        swipe.setRefreshing(false);
    }
}
