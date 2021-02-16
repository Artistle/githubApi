package com.example.githubapp.repositories

import android.content.SharedPreferences
import android.util.Log
import com.example.githubapp.database.DBItem
import com.example.githubapp.database.ItemDAO
import com.example.githubapp.injection.AppConstants
import com.example.githubapp.models.reposModel.Item
import com.example.githubapp.models.reposModel.ReposModel
import com.example.githubapp.retrofitApi.configurations.SearchConfig.searchObservable
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
    lateinit var userDao: ItemDAO
    lateinit var q:String

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
    fun setRequest(search:String?) {
        val token = sharedPreferences.getString("TOKEN","")
        searchObservable("$token",search!!).subscribeWith(getReposList())
    }
    init{
        Toothpick.inject(this, Toothpick
            .openScope(AppConstants.APPSCOPE))
    }

    private fun getReposList():DisposableSingleObserver<ReposModel>{
        return object : DisposableSingleObserver<ReposModel>(){
            override fun onSuccess(t: ReposModel) {  viewState.getReposlist(t.items)  }
            override fun onError(e: Throwable) {}
        }
    }

    //бывали случаи краша приложения из-за какого-либо пустого поля,сделал заглушку в виде ?: ""
    fun saveRepo(item: Item){
        GlobalScope.launch {
            userDao.insertAll(DBItem
                (reposName = item.name ?: "",
                fullname = item.full_name ?: "",
                desc = item.description ?: "",
                fork = item.forks ?: 0,
                stars = item.owner.id ?: 0,
                createdDate = item.created_at ?: ""))
        }


    }
}