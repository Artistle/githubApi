package com.example.githubapp.retrofitApi

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigApi {
    fun authorize() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)
}