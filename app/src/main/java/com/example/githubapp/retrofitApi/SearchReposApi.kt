package com.example.githubapp.retrofitApi

import android.content.SharedPreferences
import com.example.githubapp.models.RepositoryModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import javax.inject.Inject

interface SearchReposApi {

    @Headers("Authorization: token 25840b0b541845d50425064064551fcba0758811")
    @GET("user/repos")
    fun getRepositories(): Observable<List<RepositoryModel>>
}