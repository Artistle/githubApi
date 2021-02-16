package com.example.githubapp

import android.app.Application
import com.example.githubapp.injection.AppConstants.Companion.APPSCOPE
import com.example.githubapp.injection.AppModule
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.ktp.KTP

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