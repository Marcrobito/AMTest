package com.marcrobito.amtest

import com.marcrobito.amtest.modules.MainContract
import com.marcrobito.amtest.modules.MainModel
import com.marcrobito.amtest.modules.MainPresenter
import com.marcrobito.amtest.network.provideClient
import com.marcrobito.amtest.network.provideRetrofit
import com.marcrobito.amtest.network.provideTheMovieDBApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    factory { App() }
}

val mainModule = module{
    factory { MainPresenter() as MainContract.Presenter }
    factory { MainModel() as MainContract.Model }
}

val networkModule = module {
    single { provideClient() }
    single { provideRetrofit(get()) }
    single { provideTheMovieDBApiService(get())}
}