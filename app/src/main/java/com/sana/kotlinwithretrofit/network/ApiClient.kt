package com.sana.kotlinwithretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

     var BASE_URL = "https://api.github.com/"

        fun create(): ApiInterface{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
            return retrofit.create(ApiInterface::class.java)
        }

}