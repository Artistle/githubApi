package com.example.githubapp.authorize

import android.content.SharedPreferences
import android.util.Log
import com.example.githubapp.injection.AppConstants
import com.example.githubapp.injection.NavigationModule
import com.example.githubapp.utilits.Screen
import com.example.githubapp.models.AuthorizedDeviceModel
import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.retrofitApi.configurations.AuthorizationConfig.tokenObservable
import com.example.githubapp.retrofitApi.configurations.AuthorizationConfig.verificationObservable
import io.reactivex.observers.DisposableSingleObserver
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
            .openSubScope(AppConstants.NAVIGATION)
            .installModules(NavigationModule()))
    }

    override fun onFirstViewAttach() { super.onFirstViewAttach() }

    fun authorizeGithub(){
        verificationObservable().subscribeWith(verificationObserver())
    }
    fun authorizeGmail(){}
    fun authorizeGuest(){}

    fun getToken(){
        var code = sharedPreferences.getString("CODE",null)
        if (code != null) {
            tokenObservable(code).subscribeWith(tokenObserver())
        }
    }

    private fun tokenObserver():DisposableSingleObserver<AuthorizedDeviceModel>{
        return object : DisposableSingleObserver<AuthorizedDeviceModel>(){
            override fun onSuccess(t: AuthorizedDeviceModel) {
                if(t.access_token.isNullOrBlank()){}else{
                    sharedPreferences.edit().putString("TOKEN",t.access_token).apply()
                    router.navigateTo(Screen.Repos)
                }
            }

            override fun onError(e: Throwable) {
            }
        }
    }
    private fun verificationObserver(): DisposableSingleObserver<VerificationsModel> {
        return object : DisposableSingleObserver<VerificationsModel>(){
            override fun onSuccess(t: VerificationsModel) {
                viewState.checkCode(t.verification_uri)
                viewState.setTextCode(t.user_code)
                sharedPreferences.edit().putString("CODE","${t.device_code}").apply()
            }
            override fun onError(e: Throwable) {
            }
        }
    }

}