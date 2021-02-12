package com.example.githubapp.DI

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.githubapp.DB.AppDatabase
import com.example.githubapp.DB.UserDao
import com.example.githubapp.fragments.BaseFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.ktp.binding.module

fun AppModule(application: Application) = module {

        val context = application.applicationContext

        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database_name"
        ).build()
        var sharedPreferences = application.getSharedPreferences("TOKEN",MODE_PRIVATE)
        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
        val dao = db.userDao()
        val cicerone = Cicerone.create()
        bind(Application::class.java).toInstance(application)
        bind(Context::class.java).toInstance(context)
        bind(BaseFragment::class.java)


        bind(AppDatabase::class.java).toInstance(db)
        bind(UserDao::class.java).toInstance(dao)

        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
        bind(NavigatorHolder::class.java)

}