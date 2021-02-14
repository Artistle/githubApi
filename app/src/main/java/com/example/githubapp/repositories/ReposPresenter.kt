package com.example.githubapp.repositories

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.DI.NavigationModule
import com.example.githubapp.models.RepositoryModel
import com.example.githubapp.models.trueModels.ReposModel
import com.example.githubapp.retrofitApi.configurations.SearchConfig.search
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.observers.DisposableObserver
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.Toothpick
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class ReposPresenter(): MvpPresenter<ReposView>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val token = sharedPreferences.getString("TOKEN",null)
        Log.i("disposable_erroe","$token")

        if (token != null) {

        }else{
            Log.i("disposable_erroe","token == null")
        }
    }
    fun setRequest(search:String?){
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            subscriber.onNext(search!!)
        }).debounce(950, TimeUnit.MILLISECONDS)
            .subscribe {
                println("$it")
                //search(token).subscribeWith(getReposList())
                Log.i("OBSERVER","repos: $it")
            }
    }

    init{ Toothpick.inject(this, Toothpick
            .openScope(AppConstants.APPSCOPE)) }

    private fun updateRepoList(query:String):DisposableObserver<ReposModel>{
        return object : DisposableObserver<ReposModel>(){
            override fun onNext(t: ReposModel) {
                viewState.getReposlist(t.items)
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }
    private fun getReposList():DisposableObserver<ReposModel>{
        return object : DisposableObserver<ReposModel>(){
            override fun onNext(t: ReposModel) {
                Log.i("disposable_erroe","$t")
                viewState.getReposlist(t.items) }
            override fun onError(e: Throwable) { Log.i("disposable_erroe","${e.localizedMessage}")}
            override fun onComplete() {}
        }
    }
}