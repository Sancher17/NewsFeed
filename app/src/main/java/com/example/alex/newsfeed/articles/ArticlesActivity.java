package com.example.alex.newsfeed.articles;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.alex.newsfeed.R;
import com.example.alex.newsfeed.articleDetail.ArticleDetail;
import com.example.alex.newsfeed.dagger.AppInject;
import com.example.alex.newsfeed.retrofit.NewsItem;
import com.example.alex.newsfeed.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesActivity extends AppCompatActivity implements ArticlesContract.View{
    private String TAG = "ArticlesActivity";

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
        EventBus.getDefault().register(this);
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
                        adapter.clearAdapter();
                        presenter.loadArticles();
                    }
                }
        );

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(this, "Position " + event.message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ArticleDetail.class);
        startActivity(intent);
    }



    @Override
    public void showArticles(List<NewsItem> articles) {

    }

    @Override
    public void showArticlesEmpty() {

    }

    @Override
    public void showError() {
        Toast.makeText(this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        swipe.setRefreshing(false);
    }

    @Override
    public void updateView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
