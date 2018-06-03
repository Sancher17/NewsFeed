package com.example.alex.newsfeed.articles;

import android.util.Log;

import com.example.alex.newsfeed.BuildConfig;
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

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ArticlesPresenter extends PresenterBase<ArticlesContract.View> implements ArticlesContract.Presenter {

    private final DataManager manager;
    private Disposable mDisposable;

    private String TAG = "ArticlesPresenter";

    private final IndiaApi mIndiaApi;


    @Inject
    ArticlesAdapter adapter;

    List<NewsItem> listItems = new ArrayList<>();


    @Inject
    public ArticlesPresenter(DataManager manager, IndiaApi indiaApi) {
        this.manager = manager;
        mIndiaApi = indiaApi;
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
        mIndiaApi.newsItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Example>() {
                    @Override
                    public void onSuccess(Example newsItem) {
                        // TODO: 03.06.2018 записал данные в адаптер - для теста , (по факту надо в БД)
                        listItems.addAll(newsItem.getNewsItem());
                        adapter.items.add(listItems.get(0).getHeadLine());
                        Log.d(TAG, "onSuccess: " + listItems.get(0).getHeadLine());
                        adapter.notifyDataSetChanged();
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
