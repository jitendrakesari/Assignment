package com.example.assignment.remote;


import android.content.Context;

import com.example.assignment.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static final int TIMEOUT_PERIOD = 600;
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(TIMEOUT_PERIOD, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_PERIOD, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT_PERIOD, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
