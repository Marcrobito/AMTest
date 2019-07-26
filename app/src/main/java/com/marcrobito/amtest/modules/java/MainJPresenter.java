package com.marcrobito.amtest.modules.java;

import android.util.Log;
import com.marcrobito.amtest.pojos.MovieData;
import io.reactivex.disposables.Disposable;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Observable;

public class MainJPresenter implements MainJContract.Presenter {


    private MainJContract.Model model;
    private MainJContract.View view;



    MainJPresenter(MainJContract.Model model){
        this.model = model;

    }

    @Override
    public void setView(MainJContract.View view) {
        this.view = view;
        model.setPresenter(this);
    }

    @Override
    public void movieListFetched(List<MovieData> movieList) {
        Log.d("Presenter", movieList.toString());



        view.presentTopMovies(movieList);
    }
}
