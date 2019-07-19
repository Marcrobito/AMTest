package com.marcrobito.amtest.network

import com.marcrobito.amtest.pojos.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TheMovieDBApi {
        @GET ("top_rated?page=1")
        fun getTopRated(@Query("language") language:String,
                        @Query("api_key") apiKey:String,
                        @Query("page") page:Int): Call<Result>
}

