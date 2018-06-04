//package com.example.alex.newsfeed.retrofit;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.example.alex.testretrofit.BuildConfig;
//import com.example.alex.testretrofit.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class IndiaActivity extends AppCompatActivity {
//
//    private String TAG = "IndiaActivity";
//
//    private String BASE_URL_INDIA = "https://timesofindia.indiatimes.com/feeds/";
//
//    List<NewsItem> listItems = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_india);
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofitIndia = new Retrofit.Builder()
//                .baseUrl(BASE_URL_INDIA)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        Log.d(TAG, "onCreateIndia: ");
//
//        IndiaApi indiaApi = retrofitIndia.create(IndiaApi.class);
//
//        Call<Example> messagesIndia = indiaApi.newsItem();
//        messagesIndia.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//
//                listItems.addAll(response.body().getNewsItem());
//                printAll();
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                Log.d(TAG, "onFailureIndia: " + t);
//            }
//        });
//
//    }
//
//    void printAll(){
//        for (NewsItem list: listItems) {
//            Log.d(TAG, "list: " + list.getHeadLine());
//        }
//
//        for (int i = 0; i < 3; i++) {
//            Log.d(TAG, "list: " + listItems.get(i).getStory());
//        }
//    }
//}
