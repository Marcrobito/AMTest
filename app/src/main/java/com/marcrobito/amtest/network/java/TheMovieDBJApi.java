package com.marcrobito.amtest.network.java;

import com.marcrobito.amtest.pojos.Result;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDBJApi {
    @GET("top_rated?page=1")
    Observable<Result> getTopRated(@Query("language") String language,
                                   @Query("api_key") String apiKey,
                                   @Query("page") Integer page);
}
