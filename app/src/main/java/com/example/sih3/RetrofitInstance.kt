package com.example.sih3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    http://3.109.224.64:8080/motor/status

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://15.207.3.65:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        }
    val  apiInterface by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofit2 by lazy {
        Retrofit.Builder().baseUrl("http://3.109.224.64:8080/motor/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val  apiInterface2 by lazy {
        retrofit2.create(ApiService::class.java)
    }
}