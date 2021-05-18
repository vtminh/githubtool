package com.example.framework.model.user

import com.example.domain.base.DataResponse
import com.example.domain.user.User
import com.example.domain.user.UserRepository
import com.example.framework.model.user.remote.UserRemote

/**
 * @author tuanminh.vu
 */
class UserRepositoryImpl(
    private val remote: UserRemote
) : UserRepository {

    override fun getUserInfoFromRemote(userName: String): DataResponse<User> {
        val response = remote.getUser(userName)
        return DataResponse(data = response, isFromCache = false)
    }

    override fun getFollowerListFromRemote(userName: String): DataResponse<List<User>> {
        val response = remote.getFollowerList(userName)
        return DataResponse(data = response, isFromCache = false)
    }
}