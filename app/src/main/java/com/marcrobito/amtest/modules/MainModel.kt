package com.marcrobito.amtest.modules

import android.content.res.Resources
import android.util.Log
import com.marcrobito.amtest.App
import com.marcrobito.amtest.R
import com.marcrobito.amtest.network.TheMovieDBApi
import com.marcrobito.amtest.pojos.MovieData
import com.marcrobito.amtest.pojos.Result
import com.marcrobito.amtest.utils.Build
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class MainModel:MainContract.Model, KoinComponent{

    private val api: TheMovieDBApi by inject()

    private val app: App by inject()



    private lateinit var presenter:MainContract.Presenter

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter

        loadRequest()
    }

    private fun loadRequest(){
        var list:List<MovieData> = listOf()
        GlobalScope.launch {
            try {
                val result = callTopMoviesAsync(1)
                if (result.isSuccessful){
                    list = result.body()!!.results
                }


            }catch (e:Exception){
                Log.d("Error Tag", e.toString())
            }
        }.invokeOnCompletion {
            presenter.movieListFetched(list)
        }

    }

    private suspend fun callTopMoviesAsync(pageNumber: Int): Response<Result> = api.getTopRated(App.resourses.getString(R.string.api_locale), Build().API_KEY, pageNumber).execute()


}