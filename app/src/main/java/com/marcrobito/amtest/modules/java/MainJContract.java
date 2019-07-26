package com.marcrobito.amtest.modules.java;

import com.marcrobito.amtest.pojos.MovieData;

import java.util.List;

public interface MainJContract {

    interface View{
        void  presentTopMovies(List<MovieData> movieList);
    }

    interface Presenter{
        void setView(View view);
        void movieListFetched(List<MovieData> movieList);
    }

    interface Model{
        void setPresenter(Presenter presenter);
    }
}
