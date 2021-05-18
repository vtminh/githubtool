package com.example.githubtool.di.modules

import android.app.Application
import com.seagroup.ochacrm.framework.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * @author tuanminh.vu
 */
@Module
class AppModule(var mApplication: Application) {
    @Provides
    @ApplicationScope
    fun providesApplication(): Application {
        return mApplication
    }
}