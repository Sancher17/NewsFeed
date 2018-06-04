package com.example.alex.newsfeed.articles;

import android.util.Log;

import com.example.alex.newsfeed.arch.PresenterBase;
import com.example.alex.newsfeed.dagger.AppInject;
import com.example.alex.newsfeed.data.DataManager;
import com.example.alex.newsfeed.retrofit.Example;
import com.example.alex.newsfeed.retrofit.IndiaApi;
import com.example.alex.newsfeed.retrofit.NewsItem;
import com.example.alex.newsfeed.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ArticlesPresenter extends PresenterBase<ArticlesContract.View> implements ArticlesContract.Presenter {

    private final DataManager manager;
    private Disposable mDisposable;

    private String TAG = "ArticlesPresenter";

//    private final IndiaApi mIndiaApi; deprecated

    @Inject
    IndiaApi mIndiaApi;

    @Inject
    ArticlesAdapter adapter;




    @Inject
    public ArticlesPresenter(DataManager manager) {
        this.manager = manager;
        AppInject.getComponent().inject(this);
    }

    public void attachView(ArticlesContract.View mvpView) {
        super.attachView(mvpView);
    }

    public void detachView() {
        super.detachView();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public void loadArticles() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        final List<NewsItem> listItems = new ArrayList<>();
        mIndiaApi.newsItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Example>() {
                    @Override
                    public void onSuccess(Example newsItem) {
                        listItems.addAll(newsItem.getNewsItem());
                        Example.getInstance().setNewsItem(listItems);
                        adapter.setNewsItems((ArrayList<NewsItem>) listItems);
                        Log.d(TAG, "onSuccess: " + listItems.get(0).getHeadLine());
                        getView().updateView();
                        getView().hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError();
                    }
                });


        // так сделана в примере  android-boilerplate
//        manager.getItems()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<List<NewsItem>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(List<NewsItem> newsItems) {
//                        if (newsItems.isEmpty()) {
//                            getView().showArticlesEmpty();
//                        } else {
//                            getView().showArticles(newsItems);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getView().showError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


    }


}
