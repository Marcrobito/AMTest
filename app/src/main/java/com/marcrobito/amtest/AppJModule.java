package com.marcrobito.amtest;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


@Module
public class AppJModule {
    private Application app;

    void AppJModule(Application app){
        this.app = app;
    }

    @Provides
    @Singleton
    Application providesApp(){
        return app;
    }


}
