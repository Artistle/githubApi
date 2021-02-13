package com.example.githubapp.authorize

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.DI.NavigationModule
import com.example.githubapp.Screen
import com.example.githubapp.models.AuthorizedDeviceModel
import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.presenter.BasePresenter
import com.example.githubapp.retrofitApi.ConfigApi
import com.example.githubapp.retrofitApi.configurations.Authorization.checkToken
import com.example.githubapp.retrofitApi.configurations.Authorization.getObservable
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

    @Inject
    lateinit var router: Router


    init {
        Toothpick.inject(this,Toothpick
            .openScope(AppConstants.APPSCOPE)
            .openSubScope("main")
            .installModules(NavigationModule()))
    }

    override fun onFirstViewAttach() { super.onFirstViewAttach() }

    fun authorizeGithub(){
        getObservable().subscribeWith(getObserver())
    }
    fun authorizeGmail(){}
    fun authorizeGuest(){}

    fun getToken(){
        var code = sharedPreferences.getString("CODE",null)
        if (code != null) {
            checkToken(code).subscribeWith(observerCheckToken())
        }
    }

    private fun observerCheckToken():DisposableSingleObserver<AuthorizedDeviceModel>{
        return object : DisposableSingleObserver<AuthorizedDeviceModel>(){
            override fun onSuccess(t: AuthorizedDeviceModel) {
                if(t.access_token.isNullOrBlank()){

                }else{
                    sharedPreferences.edit().putString("TOKEN",t.access_token).apply()
                    router.navigateTo(Screen.Repos)
                }

                Log.i("OBSERVER","$t")
            }

            override fun onError(e: Throwable) {
                Log.i("OBSERVER","$e")
            }
        }
    }
    private fun getObserver(): DisposableSingleObserver<VerificationsModel> {
        return object : DisposableSingleObserver<VerificationsModel>(){
            override fun onSuccess(t: VerificationsModel) {
                viewState.startCheck(t)
                viewState.setTextCode(t.user_code)
                sharedPreferences.edit().putString("CODE","${t.device_code}").apply()
                Log.i("OBSERVER","$t")
            }

            override fun onError(e: Throwable) {
                Log.i("OBSERVER","$e")
            }
        }
    }

}