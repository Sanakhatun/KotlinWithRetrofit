package com.sana.kotlinwithretrofit

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val username: String,
    @SerializedName("type")
    val userType: String,
    @SerializedName("avatar_url")
    val image: String
)