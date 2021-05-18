package com.example.domain.user

import com.example.domain.base.DataResponse

interface UserRepository {
    fun getUserInfoFromRemote(userName: String): DataResponse<User>
    fun getFollowerListFromRemote(userName: String): DataResponse<List<User>>
}