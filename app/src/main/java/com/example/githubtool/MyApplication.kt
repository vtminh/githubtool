package com.example.githubtool

import android.app.Application
import com.example.framework.module.NetworkModule
import com.example.framework.module.RepositoryModule
import com.example.framework.network.NetworkStateHandler
import com.example.githubtool.di.components.AppComponent
import com.example.githubtool.di.components.DaggerAppComponent
import com.example.githubtool.di.modules.AppModule


/**
 * Created by tuanminh.vu
 */
class MyApplication : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        NetworkStateHandler.initialize(this)
        getAppComponent().inject(this)
    }

    fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule())
                .build()
        }
        return appComponent!!
    }
}
