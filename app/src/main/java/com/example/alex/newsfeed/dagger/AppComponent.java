package com.example.alex.newsfeed.dagger;

import com.example.alex.newsfeed.articles.ArticlesActivity;
import com.example.alex.newsfeed.articles.ArticlesAdapter;
import com.example.alex.newsfeed.articles.ArticlesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ArticlesModule.class})
@Singleton
public interface AppComponent {

    void inject(ArticlesActivity articles);
    void inject(ArticlesPresenter articles);


}

