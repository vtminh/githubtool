package com.example.framework.model

import com.example.domain.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author tuanminh.vu
 */
interface MRService {

    @GET("users/{user_name}")
    fun getUser(@Path("user_name") userName: String): Call<User>

    @GET("users/{user_name}/followers")
    fun getFollowerList(@Path("user_name") userName: String): Call<List<User>>
}