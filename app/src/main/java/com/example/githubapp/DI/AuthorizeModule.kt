package com.example.githubapp.DI

import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.retrofitApi.Api
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.ktp.binding.module

fun AuthorizeModule() = module{
    fun authorize() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)
}