package com.marcrobito.amtest;

import android.app.Application;
import android.content.res.Resources;
import com.marcrobito.amtest.modules.java.MainModule;
import com.marcrobito.amtest.network.java.NetworkJModule;


public class AppJava extends Application {

    private AppJComponent component;

    public Resources res;

    @Override
    public void onCreate() {
        super.onCreate();

        res = getResources();

        component = DaggerAppJComponent.builder()
                .networkJModule(new NetworkJModule())
                .mainModule(new MainModule(this ))
                .build();

        getComponent().inject(this);

    }

    public AppJComponent getComponent(){
        return component;
    }
}
