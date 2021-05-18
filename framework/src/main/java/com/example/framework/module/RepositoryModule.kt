package com.example.framework.module

import com.example.domain.user.UserRepository
import com.example.framework.model.user.UserRepositoryImpl
import com.example.framework.model.user.remote.UserRemote
import com.seagroup.ochacrm.framework.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * @author tuanminh.vu
 */
@Module
open class RepositoryModule {

    @ApplicationScope
    @Provides
    fun provideUserRepository(remote: UserRemote): UserRepository {
        return UserRepositoryImpl(remote)
    }

}