package com.example.githubapp.retrofitApi

import com.example.githubapp.models.AuthorizedDeviceModel
import com.example.githubapp.models.VerificationsModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizateApi {

    @Headers("Accept: application/vnd.github.v3+json")
    @FormUrlEncoded
    @POST("login/device/code")
    fun verification(@Field("client_id")  client_id:String ): Single<VerificationsModel>

    @Headers("Accept: application/vnd.github.v3+json")
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    fun authorizate(
        @Field("device_code") deviceCode:String,
        @Field("client_id") clientId:String = "88080cc156512ce353ce",
        @Field("grant_type") grantType:String = "urn:ietf:params:oauth:grant-type:device_code"
    ):Single<AuthorizedDeviceModel>


}