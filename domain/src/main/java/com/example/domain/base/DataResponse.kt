package com.example.domain.base


open class DataResponse<T>(var errorMsg: String? = "",
                           var errorCode: Int = 0,
                           var error : Throwable? = null,
                           var data: T? = null,
                           var isFromCache : Boolean = false) {

    fun isSuccess() : Boolean {
        return error == null && errorCode == 0 && data != null
    }

}