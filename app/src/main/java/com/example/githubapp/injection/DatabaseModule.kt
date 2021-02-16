package com.example.githubapp.injection

import android.app.Application
import androidx.room.Room
import com.example.githubapp.database.AppDatabase
import com.example.githubapp.database.ItemDAO
import toothpick.ktp.binding.module


fun InitDatabase(application: Application) = module {

    val context = application.applicationContext

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "database_name"
    ).build()
    //val compositeDisposable = CompositeDisposable()
    //var sharedPreferences = application.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
    //bind(SharedPreferences::class.java).toInstance(sharedPreferences)
    val dao = db.userDao()
    //val cicerone = Cicerone.create()
    //bind(Application::class.java).toInstance(application)
    //bind(Context::class.java).toInstance(context)
    //bind(CompositeDisposable::class.java).toInstance(compositeDisposable)

    bind(AppDatabase::class.java).toInstance(db)
    bind(ItemDAO::class.java).toInstance(dao)
}