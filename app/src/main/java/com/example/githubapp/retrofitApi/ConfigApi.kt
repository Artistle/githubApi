package com.example.githubapp.retrofitApi

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigApi {
    fun authorizationConfig() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AuthorizationApi::class.java)

    fun searchConfig(token:String) = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.github.com/")
        .client(interceptor(token).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(SearchReposApi::class.java)

    private fun interceptor(token:String): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder().addInterceptor {
            var request: Request = it.request().newBuilder().addHeader("Authorization", "token $token").build()
            return@addInterceptor it.proceed(request)
        }
        return httpClient
    }
}