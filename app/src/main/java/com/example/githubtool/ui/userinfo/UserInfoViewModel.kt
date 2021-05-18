package com.example.githubtool.ui.userinfo

import android.app.Application
import com.example.domain.user.GetUserInfoUseCase
import com.example.domain.user.User
import com.example.githubtool.base.BaseViewModel
import javax.inject.Inject

/**
 * @author tuanminh.vu
 */
class UserInfoViewModel(context: Application) : BaseViewModel<User>(context) {
    @Inject
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    fun getUserInfo(userName: String) {
        getUserInfoUseCase.userName = userName
        loadData(dataSource = getUserInfoUseCase)
    }
}