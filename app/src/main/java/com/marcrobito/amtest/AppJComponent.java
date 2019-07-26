package com.marcrobito.amtest;

import com.marcrobito.amtest.modules.java.MainActivity;
import com.marcrobito.amtest.modules.java.MainModule;
import dagger.Component;
import com.marcrobito.amtest.network.java.NetworkJModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppJModule.class, NetworkJModule.class, MainModule.class})

public interface AppJComponent {
    void inject(MainActivity main);
    void inject(AppJava app);
}
