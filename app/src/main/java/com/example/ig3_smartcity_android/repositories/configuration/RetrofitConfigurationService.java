package com.example.ig3_smartcity_android.repositories.configuration;

import android.content.Context;

import com.example.ig3_smartcity_android.repositories.webservice.WebServives;
import com.example.ig3_smartcity_android.utils.ConnectivityCheckInterceptor;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitConfigurationService {

    private static final String BASE_URL ="http://localhost:3001/";

    private Retrofit retrofitClient;

    private static WebServives webServives = null;
    //private static WebServices webServices = null; --> this one was written in Java

    private RetrofitConfigurationService(Context context){
        inittializeRetrofit(context);
    }

    private void inittializeRetrofit(Context context){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityCheckInterceptor(context))
                .build();

        Moshi moshiConverter = new Moshi.Builder()
                .add(new KotlinJsonAdapterFactory())
                .build();

        this.retrofitClient = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
                .build();
    }

    public static RetrofitConfigurationService getInstance(Context context){
        return new RetrofitConfigurationService(context);
    }

    public WebServives webServives(){
        if(webServives == null){
            webServives = retrofitClient.create((WebServives.class));
        }
        return webServives;
    }
}
