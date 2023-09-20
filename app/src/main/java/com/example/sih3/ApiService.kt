package com.example.sih3

import android.net.http.UrlRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("get_data")
    fun getData(): Call<ResponseData>


    @GET("status")
    fun getStatus():Call<StatusResponseData>


    @POST("update")
    fun postUpdate(@Body status : StatusResponseData ):Call<StatusResponseData>
}