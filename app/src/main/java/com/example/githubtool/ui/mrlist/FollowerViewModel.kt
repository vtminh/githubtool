package com.example.githubtool.ui.mrlist

import android.app.Application
import com.example.domain.user.GetFollowerListUseCase
import com.example.domain.user.User
import com.example.githubtool.base.BaseViewModel
import javax.inject.Inject

/**
 * @author tuanminh.vu
 */
class FollowerViewModel(context: Application) : BaseViewModel<List<User>>(context) {
    @Inject
    lateinit var getFollowerListUseCase: GetFollowerListUseCase

    fun getFollowerList(userName:String) {
        getFollowerListUseCase.userName = userName
        loadData(dataSource = getFollowerListUseCase)
    }
}