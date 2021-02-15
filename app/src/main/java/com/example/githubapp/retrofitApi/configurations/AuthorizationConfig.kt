package com.example.githubapp.retrofitApi.configurations

import com.example.githubapp.models.AuthorizedDeviceModel
import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.retrofitApi.ConfigApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AuthorizationConfig {
    fun checkToken(code:String): Single<AuthorizedDeviceModel> {
        return ConfigApi()
            .authorize()
            .authorizate(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getObservable(): Single<VerificationsModel> {
        return ConfigApi()
            .authorize()
            .verification("88080cc156512ce353ce")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}