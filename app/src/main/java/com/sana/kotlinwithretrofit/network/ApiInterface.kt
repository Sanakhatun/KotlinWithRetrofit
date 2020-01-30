package com.sana.kotlinwithretrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getImages(): Call<List<User>>
}