package com.example.githubtool.base

import android.content.Context
import com.example.domain.base.NoNetWorkException
import com.example.githubtool.R
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author tuanminh.vu
 */
class ErrorMapper {
    companion object {
        fun getErrorMessage(e: Throwable?, context: Context): String {
            if (isNoInternetException(e)) {
                return context.getString(R.string.str_no_internet)
            }
            return context.getString(R.string.str_something_went_wrong)
        }

        private fun isNoInternetException(e: Throwable?): Boolean {
            return e is SocketTimeoutException || e is UnknownHostException || e is SocketException || e is NoNetWorkException
        }
    }
}