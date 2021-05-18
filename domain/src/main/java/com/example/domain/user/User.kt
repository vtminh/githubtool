package com.example.domain.user

import com.example.domain.base.BaseDataModel
import com.google.gson.annotations.SerializedName

/**
 * @author tuanminh.vu
 */
open class User(
    @SerializedName("id") open var id: Int = 0,
    @SerializedName("login") var login: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
    @SerializedName("followers") var followers: String? = null,
    @SerializedName("following") var following: String? = null,
    @SerializedName("type") var type: String? = null
): BaseDataModel()