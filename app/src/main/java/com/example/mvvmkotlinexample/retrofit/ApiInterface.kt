package com.example.mvvmkotlinexample.retrofit

import com.example.mvvmkotlinexample.model.ImgurImageDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {
    @GET("images")
    fun getServices() : Call<ImgurImageDto>

}