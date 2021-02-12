package com.example.githubapp

import android.app.Application
import com.example.githubapp.DI.AppConstants.Companion.APPSCOPE
import com.example.githubapp.DI.AppModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.configuration.Configuration
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class App : Application() {
    //private var cicerone = Cicerone.create()


    override fun onCreate() {
        super.onCreate()
        initToothpick()
    }

    private fun initToothpick(){
        Toothpick.setConfiguration(Configuration.forProduction()
            .preventMultipleRootScopes())
        KTP.openScope(APPSCOPE)
            .installModules(AppModule(this))
    }

}