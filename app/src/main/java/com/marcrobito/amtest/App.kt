package com.marcrobito.amtest

import android.app.Application
import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.context.startKoin



class App:Application() {

    companion object {
        lateinit var instance: Application
        lateinit var res: Resources
    }


    private val appModules: List<Module> = listOf(appModule, mainModule, networkModule)


    override fun onCreate() {
        super.onCreate()
        instance = this
        res = resources
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }

}