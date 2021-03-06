package com.example.githubapp.main

import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.githubapp.injection.AppConstants
import com.example.githubapp.injection.NavigationModule
import com.example.githubapp.R
import com.example.githubapp.utilits.Screen
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class MainPresenter():MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var navigator: Navigator

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        var token = sharedPreferences.getString("TOKEN",null)
//        sharedPreferences.edit().clear().apply()
        println("$token")
        if(token.isNullOrBlank()){
            replaceAuthorize()
        }else{
            replaceRepos()
        }
    }

    init{
        Toothpick.inject(this,Toothpick
                .openScope(AppConstants.APPSCOPE)
                .openSubScope(AppConstants.NAVIGATION)
                .installModules(NavigationModule()))
    }
    fun initPresenter(context: FragmentActivity){
        navigator = SupportAppNavigator(context, R.id.container)
        navigatorHolder.setNavigator(navigator)
    }

    fun onBackPressed(){}
    fun replaceFavorites(){router.navigateTo(Screen.SaveRepos)}
    fun replaceRepos(){ router.navigateTo(Screen.Repos) }
    fun replaceAuthorize(){ router.navigateTo(Screen.Authorize) }
}