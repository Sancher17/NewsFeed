package com.example.alex.newsfeed.retrofit;

import com.example.alex.newsfeed.BuildConfig;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface IndiaApi {

    String BASE_URL = "https://timesofindia.indiatimes.com/feeds/";

    @GET("newsdefaultfeeds.cms?feedtype=sjson")
    Single<Example> newsItem();

   class Creator {

       public static IndiaApi newIndiaApi(){

           HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
           interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

           OkHttpClient client = new OkHttpClient.Builder()
                   .addInterceptor(interceptor)
                   .build();

           Retrofit retrofitIndia = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   .client(client)
                   .build();

           return retrofitIndia.create(IndiaApi.class);
       }
   }
}
