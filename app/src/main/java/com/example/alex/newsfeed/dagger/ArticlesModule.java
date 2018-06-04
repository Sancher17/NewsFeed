package com.example.alex.newsfeed.dagger;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.alex.newsfeed.articleDetail.ArticleDetail;
import com.example.alex.newsfeed.articles.ArticlesAdapter;
import com.example.alex.newsfeed.articles.ArticlesContract;
import com.example.alex.newsfeed.articles.ArticlesPresenter;
import com.example.alex.newsfeed.data.DataManager;
import com.example.alex.newsfeed.retrofit.IndiaApi;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ArticlesModule {

    private String TAG = "ArticlesModule";
    @Singleton
    DataManager manager = new DataManager();

    @Provides
    @Singleton
    IndiaApi provideIndiaApi() {
        return IndiaApi.Creator.newIndiaApi();
    }

    @Singleton
    @Provides
    ArticlesContract.Presenter provideArticlesPresenter(){
        return new ArticlesPresenter(manager);
    }


    @Singleton
    @Provides
    ArticlesAdapter provideArticlesAdapter(){
        return new ArticlesAdapter();
    }

}
