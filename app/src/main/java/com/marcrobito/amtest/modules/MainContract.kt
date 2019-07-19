package com.marcrobito.amtest.modules

import com.marcrobito.amtest.pojos.MovieData


interface MainContract {

    interface View{
        //var presenter:MainContract.Presenter
        fun presentTopMovies(movieList:List<MovieData>)
    }

    interface Presenter{
        fun setView(view: View)
        fun movieListFetched(movieList:List<MovieData>)
    }

    interface Model{
        fun setPresenter(presenter: Presenter)
    }
}

