package com.example.alex.newsfeed.data;

import android.util.Log;

import com.example.alex.newsfeed.BuildConfig;
import com.example.alex.newsfeed.articles.ArticlesAdapter;
import com.example.alex.newsfeed.retrofit.Example;
import com.example.alex.newsfeed.retrofit.IndiaApi;
import com.example.alex.newsfeed.retrofit.NewsItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {

    private String TAG = "DataManager";

    private String BASE_URL = "https://timesofindia.indiatimes.com/feeds/";

    ArticlesAdapter adapter = new ArticlesAdapter();

    List<NewsItem> listItems = new ArrayList<>();

    @Inject
    public DataManager() {
    }

    //    public Observable<List<NewsItem>> getItems() {
    public void getItems() {






//        Call<Example> messagesIndia = indiaApi.newsItem();
//        messagesIndia.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//
//                listItems.addAll(response.body().getNewsItem());
//                printAll();
//                swipe.setRefreshing(false);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                Log.d(TAG, "onFailureIndia: " + t);
//            }
//        });


//        return mDatabaseHelper.getRibots().distinct();
    }

}
