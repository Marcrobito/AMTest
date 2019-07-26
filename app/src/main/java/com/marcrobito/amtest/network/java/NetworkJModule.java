package com.marcrobito.amtest.network.java;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkJModule {

    private Application app;

    private String baseUrl = "https://api.themoviedb.org/3/movie/";



    @Provides
    OkHttpClient provideClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);



        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(interceptor).build();
                //.cache(cache)
/*
                .addInterceptor {
            try{
                it.proceed(it.request())
            }catch (e:Exception){
                val offlineRequest = it.request().newBuilder()
                        .header("Cache-Control", "public, only-if-cached," +
                                "max-stale=" + 60 * 60 * 24)

                        .build()
                it.proceed(offlineRequest)
            }
        }.build()*/
    }

    @Provides
    Retrofit provideRetrofit(String baseUrl, OkHttpClient client){


        return  new Retrofit.Builder()

                .baseUrl(baseUrl)
                .client(client)

                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    TheMovieDBJApi provideTheMovieDBApiService(){
        return provideRetrofit(baseUrl, provideClient()).create(TheMovieDBJApi.class);
    }



}
