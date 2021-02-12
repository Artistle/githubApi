package com.example.githubapp.authorize

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.Screen
import com.example.githubapp.models.AuthorizedDeviceModel
import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.presenter.BasePresenter
import com.example.githubapp.retrofitApi.ConfigApi
import com.example.githubapp.system.FlowRouter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class AuthorizePresenter():MvpPresenter<AuthorizeView>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        Toothpick.inject(this,Toothpick
                .openScope(AppConstants.APPSCOPE))
    }

    override fun onFirstViewAttach() { super.onFirstViewAttach() }

//    fun onClick() = router.startFlow(Screens.AuthFlow)
//    fun onBackPressed() = router.exit()

    fun authorizeGithub(){
        getObservable().subscribeWith(getObserver())
    }
    fun authorizeGmail(){}
    fun authorizeGuest(){}

    fun checkToken(){}

    private fun checkToken(deviceCode:String):Single<AuthorizedDeviceModel>{
        return ConfigApi()
            .authorize()
            .authorizate(deviceCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    private fun observerCheckToken():DisposableSingleObserver<AuthorizedDeviceModel>{
        return object : DisposableSingleObserver<AuthorizedDeviceModel>(){
            override fun onSuccess(t: AuthorizedDeviceModel) {

            }

            override fun onError(e: Throwable) {

            }

        }
    }


    private fun getObservable(): Single<VerificationsModel> {
        return ConfigApi()
            .authorize()
            .verification("88080cc156512ce353ce")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    private fun getObserver(): DisposableSingleObserver<VerificationsModel> {
        return object : DisposableSingleObserver<VerificationsModel>(){
            override fun onSuccess(t: VerificationsModel) {
                viewState.startCheck(t)
                Log.i("OBSERVER","$t")
            }

            override fun onError(e: Throwable) {
                Log.i("OBSERVER","$e")
            }
        }
    }

}