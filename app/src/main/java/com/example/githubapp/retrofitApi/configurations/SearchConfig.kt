package com.example.githubapp.retrofitApi.configurations
import com.example.githubapp.models.trueModels.ReposModel
import com.example.githubapp.retrofitApi.ConfigApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SearchConfig{
    fun search(token:String): Observable<ReposModel> {
        return ConfigApi()
            .search(token)
            .getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun searchTest(token:String,query:String): Single<ReposModel> {
        return ConfigApi()
            .searchTest(token)
            .getRepositoriesTest(q = query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}