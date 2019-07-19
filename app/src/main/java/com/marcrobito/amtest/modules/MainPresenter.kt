package com.marcrobito.amtest.modules

import android.util.Log
import com.marcrobito.amtest.pojos.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter:MainContract.Presenter, KoinComponent {

    private var job: Job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private val model:MainContract.Model by inject()

    private lateinit var view:MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
        model.setPresenter(this)
    }

    override fun movieListFetched(movieList: List<MovieData>) {
        scope.launch {
            view.presentTopMovies(movieList)
        }

    }


}