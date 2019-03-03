package com.example.recyclerviewapi.network

import com.example.recyclerviewapi.entity.Members
import com.example.recyclerviewapi.utils.Pref
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MainService {

    @GET("get-member")
    fun getMemberAsync(): Deferred<Response<Members>>

    companion object {
        operator fun invoke(): MainService = Retrofit.Builder()
            .baseUrl(Pref.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(MainService::class.java)

    }
}