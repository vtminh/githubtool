package com.example.githubtool.di.components

import com.example.framework.module.NetworkModule
import com.example.framework.module.RepositoryModule
import com.example.githubtool.MyApplication
import com.example.githubtool.base.BaseActivity
import com.example.githubtool.di.modules.AppModule
import com.example.githubtool.ui.mrlist.FollowerViewModel
import com.example.githubtool.ui.userinfo.UserInfoViewModel
import com.seagroup.ochacrm.framework.di.scopes.ApplicationScope
import dagger.Component


/**
 * @author tuanminh.vu
 */
@ApplicationScope
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(viewModel: MyApplication)
    fun inject(baseActivity: BaseActivity)
    fun inject(viewModel: FollowerViewModel)
    fun inject(viewModel: UserInfoViewModel)

}