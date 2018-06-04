package com.example.alex.newsfeed.articles;

import com.example.alex.newsfeed.arch.MvpPresenter;
import com.example.alex.newsfeed.arch.MvpView;
import com.example.alex.newsfeed.data.Articles;
import com.example.alex.newsfeed.retrofit.NewsItem;

import java.util.List;

public interface ArticlesContract {

    interface View extends MvpView {

        void showArticles(List<NewsItem> articles);

        void showArticlesEmpty();

        void showError();

        void hideProgressBar();

        void updateView();

    }

    interface Presenter extends MvpPresenter<View>{

        void loadArticles();



    }
}
