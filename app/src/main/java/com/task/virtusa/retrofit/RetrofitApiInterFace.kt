package com.task.virtusa.retrofit

import com.task.virtusa.model.GetData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiInterFace {

    @GET("users/{id}")
    suspend fun getData(@Path("id") id :String): Response<GetData>

}