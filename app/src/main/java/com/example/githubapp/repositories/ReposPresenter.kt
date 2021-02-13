package com.example.githubapp.repositories

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.DI.NavigationModule
import com.example.githubapp.models.RepositoryModel
import com.example.githubapp.retrofitApi.configurations.SearchConfig.search
import io.reactivex.observers.DisposableObserver
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class ReposPresenter(): MvpPresenter<ReposView>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        var token = sharedPreferences.getString("TOKEN",null)
        Log.i("disposable_erroe","$token")

        if (token != null) {
            search(token).subscribeWith(getReposList())
        }else{
            Log.i("disposable_erroe","ошибка нахой блеать")
        }


    }

    init{
        Toothpick.inject(this, Toothpick
            .openScope(AppConstants.APPSCOPE))
//            .openSubScope("main")
//            .installModules(NavigationModule()))
    }


    fun getReposList():DisposableObserver<List<RepositoryModel>>{
        return object : DisposableObserver<List<RepositoryModel>>(){
            override fun onNext(t: List<RepositoryModel>) {
                Log.i("disposable_erroe","$t")
                viewState.getReposlist(t)
            }

            override fun onError(e: Throwable) { Log.i("disposable_erroe","${e.localizedMessage}")}

            override fun onComplete() {}
        }
    }
}