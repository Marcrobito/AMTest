package com.marcrobito.amtest.modules.java;

import android.app.Application;
import com.marcrobito.amtest.network.java.TheMovieDBJApi;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;

@Module
public class MainModule {

    private Application app;

    public MainModule( Application app){
        this.app = app;
    }

    @Provides
    MainJContract.Presenter providesMainPresenter(MainJContract.Model model){
        return new MainJPresenter(model);
    }

    @Provides
    @Inject
    MainJContract.Model providesMainModel(TheMovieDBJApi api){
        return new MainJModel(api, app);
    }


}
