package com.example.alex.newsfeed.arch;


public interface MvpPresenter <V extends MvpView>  {

    void attachView(V mvpView);

    void detachView();

}
