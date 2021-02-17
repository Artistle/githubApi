package com.example.githubapp.retrofitApi.configurations
import com.example.githubapp.models.reposModel.ReposModel
import com.example.githubapp.retrofitApi.ConfigApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SearchConfig{
    fun searchObservable(token:String, query:String): Single<ReposModel> {
        return ConfigApi()
            .searchConfig(token)
            .getRepositories(q = query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}


