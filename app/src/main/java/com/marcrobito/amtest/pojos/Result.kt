package com.marcrobito.amtest.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("page") @Expose  val page:Int,
    @SerializedName("total_results") @Expose  val totalResults:Int,
    @SerializedName("total_pages") @Expose  val totalPages:Int,
    @SerializedName("results") @Expose  val results:List<MovieData>
)

data class MovieData(
    @SerializedName("vote_count") @Expose  val voteCount:Int,
    @SerializedName("id") @Expose  val id:Int,
    @SerializedName("video") @Expose  val video:Boolean,
    @SerializedName("vote_average") @Expose  val voteAverage:Double,
    @SerializedName("title") @Expose  val title:String,
    @SerializedName("popularity") @Expose  val popularity:Double,
    @SerializedName("poster_path") @Expose  val posterUrl:String,
    @SerializedName("original_language") @Expose  val originalLanguage:String,
    @SerializedName("original_title") @Expose  val originalTitle:String,
    //@SerializedName("genre_ids") @Expose  val genreIds:GenreIds,
    @SerializedName("backdrop_path") @Expose  val backdropPath:String,
    @SerializedName("adult") @Expose  val adult:Boolean,
    @SerializedName("overview") @Expose  val overview:String,
    @SerializedName("release_date") @Expose  val releaseDate:String

)

data class  GenreIds(private val genres:Array<Int>)

