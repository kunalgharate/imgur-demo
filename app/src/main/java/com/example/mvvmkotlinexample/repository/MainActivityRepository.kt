package com.example.mvvmkotlinexample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinexample.model.ImgurImageDto
import com.example.mvvmkotlinexample.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<ImgurImageDto>()

    fun getServicesApiCall(): MutableLiveData<ImgurImageDto> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<ImgurImageDto> {
            override fun onFailure(call: Call<ImgurImageDto>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ImgurImageDto>,
                response: Response<ImgurImageDto>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                serviceSetterGetter.value = data
            }
        })

        return serviceSetterGetter
    }
}