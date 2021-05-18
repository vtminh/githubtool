package com.example.framework.network

import com.example.domain.base.NoNetWorkException
import retrofit2.Call

/**
 * @author tuanminh.vu
 */
class NetworkUtils {
    companion object {
        fun <T> handleServerResponse(request: Call<T>): T {
            if (!NetworkStateHandler.isNetworkAvailable()) {
                throw NoNetWorkException()
            }
            val response = request.execute()
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            } else {
                throw Exception("Something went wrong")
            }
        }
    }
}