package com.example.framework.model.user.remote

import com.example.domain.user.User
import com.example.framework.model.MRService
import com.example.framework.network.NetworkUtils
import javax.inject.Inject

/**
 * @author tuaninh.vu
 */

class UserRemote @Inject constructor(private val service: MRService) {

    fun getUser(userName: String): User {
        return NetworkUtils.handleServerResponse(service.getUser(userName))
    }

    fun getFollowerList(userName: String): List<User> {
        return NetworkUtils.handleServerResponse(service.getFollowerList(userName))
    }
}