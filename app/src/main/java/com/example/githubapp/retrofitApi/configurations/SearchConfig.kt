package com.example.githubapp.retrofitApi.configurations

import com.example.githubapp.models.AuthorizedDeviceModel
import com.example.githubapp.models.RepositoryModel
import com.example.githubapp.retrofitApi.ConfigApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SearchConfig{
    fun search(token:String): Observable<List<RepositoryModel>> {
        return ConfigApi()
            .search(token)
            .getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}