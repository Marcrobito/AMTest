package com.marcrobito.amtest.network

import com.marcrobito.amtest.utils.Build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(interceptor)
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
        }.build()
}

fun provideRetrofit(client:OkHttpClient): Retrofit {


    return  Retrofit.Builder()

        .baseUrl(Build().BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


fun provideTheMovieDBApiService(retrofit: Retrofit):TheMovieDBApi = retrofit.create(TheMovieDBApi::class.java)
