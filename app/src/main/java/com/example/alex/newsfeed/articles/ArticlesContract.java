package com.example.alex.newsfeed.articles;

import com.example.alex.newsfeed.arch.MvpPresenter;
import com.example.alex.newsfeed.arch.MvpView;

public interface ArticlesContract {

    interface View extends MvpView {

    }

    interface Presenter extends MvpPresenter<View>{


    }
}
