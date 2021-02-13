package com.example.githubapp.retrofitApi

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigApi {
    fun authorize() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AuthorizateApi::class.java)



    fun search(token:String) = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        //.client(interceptor(token))
        .build().create(SearchReposApi::class.java)

    private fun interceptor(token:String): OkHttpClient {
        val httpClient = OkHttpClient.Builder().addInterceptor {
            var request: Request = it.request().newBuilder().addHeader("", "").build()
            return@addInterceptor it.proceed(request)
        }.build()

        return httpClient
    }
}