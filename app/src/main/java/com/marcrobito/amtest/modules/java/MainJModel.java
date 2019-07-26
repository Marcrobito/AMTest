package com.marcrobito.amtest.modules.java;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import com.marcrobito.amtest.AppJava;
import com.marcrobito.amtest.R;
import com.marcrobito.amtest.network.java.TheMovieDBJApi;
import com.marcrobito.amtest.pojos.MovieData;
import com.marcrobito.amtest.pojos.Result;
import com.marcrobito.amtest.utils.Build;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class MainJModel implements MainJContract.Model {

    private MainJContract.Presenter presenter;
    private TheMovieDBJApi api;
    private AppJava app;
    private Observable<Result> observable;




    MainJModel(TheMovieDBJApi api, Application app){
        this.api = api;
        this.app = (AppJava) app ;
    }




    @Override
    public void setPresenter(MainJContract.Presenter presenter) {
        this.presenter = presenter;
        loadRequest();
    }

    @SuppressLint("CheckResult")
    private void loadRequest(){
        observable = api.getTopRated(app.res.getString(R.string.api_locale), new Build().getAPI_KEY(), 1);

        observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(Result::getResults)
                    .subscribe(presenter::movieListFetched,this::handleError);




    }

    private void handleError(Throwable e){

        Log.e("Error", e.toString());
    }






}
