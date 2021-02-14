package com.example.githubapp.retrofitApi

import com.example.githubapp.models.trueModels.ReposModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface SearchReposApi {

   //@Headers("Authorization: token 25840b0b541845d50425064064551fcba0758811")
    @GET("search/repositories")
    fun getRepositories(@Query("q")q:String="q",
                        @Query("page")page:Int=1,
                        @Query("per_page")perPage:Int=10): Observable<ReposModel>

    @GET("search/repositories")
    fun getRepositoriesTest(@Query("q")q:String="q",
                        @Query("page")page:Long=1,
                        @Query("per_page")perPage:Int=10): Single<ReposModel>
}