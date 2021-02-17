package com.example.githubapp.retrofitApi

import com.example.githubapp.models.reposModel.ReposModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface SearchReposApi {

    @GET("search/repositories")
    fun getRepositories(@Query("q")q:String,
                        @Query("page")page:Long=1,
                        @Query("per_page")perPage:Int=10): Single<ReposModel>

}