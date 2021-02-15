package com.example.githubapp.repositories

import android.content.SharedPreferences
import android.util.Log
import com.example.githubapp.DB.DBItem
import com.example.githubapp.DB.UserDao
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.models.trueModels.Item
import com.example.githubapp.models.trueModels.ReposModel
import com.example.githubapp.retrofitApi.configurations.SearchConfig.searchTest
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class ReposPresenter(): MvpPresenter<ReposView>() {

    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    //var t = sharedPreferences.getString("TOKEN","")
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val token = sharedPreferences.getString("TOKEN",null)
        Log.i("disposable_erroe","$token")

        if (token != null) {

        }else{
            Log.i("disposable_erroe","token == null")
        }
    }
    fun setRequest(search:String?) {
        searchTest("${sharedPreferences.getString("TOKEN","")}",search!!).subscribeWith(getReposList())
    }
    init{
        Toothpick.inject(this, Toothpick
            .openScope(AppConstants.APPSCOPE))
    }
    private fun updateRepoList(query:String):DisposableObserver<ReposModel>{
        return object : DisposableObserver<ReposModel>(){
            override fun onNext(t: ReposModel) {
                viewState.getReposlist(t.items)
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }
    private fun getReposList():DisposableSingleObserver<ReposModel>{
        return object : DisposableSingleObserver<ReposModel>(){
            override fun onSuccess(t: ReposModel) {  viewState.getReposlist(t.items)  }
            override fun onError(e: Throwable) {}

        }
    }

    fun saveRepo(item: Item){
        GlobalScope.launch {
            userDao.insertAll(DBItem(reposName = item.name,fullname = item.full_name,desc = item.description,fork = item.forks,stars = item.owner.id))
            Log.i("DB","${userDao.gelAll()}")
        }


    }
}