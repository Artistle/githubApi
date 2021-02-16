package com.example.githubapp.savedRepos

import android.util.Log
import com.example.githubapp.database.DBItem
import com.example.githubapp.database.ItemDAO
import com.example.githubapp.injection.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class SaveReposPresenter:MvpPresenter<SaveReposView>() {


    @Inject
    lateinit var userDao: ItemDAO


    init{
        Toothpick.inject(this, Toothpick
                .openScope(AppConstants.APPSCOPE))
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getSingle()
    }

    private fun getSingle() {
        userDao.gelAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                       viewState.setList(it)
                    Log.i("DB","$it")
                },{
                    Log.i("DB","ERROR LOAD FROM DATABASE")
                })
    }

    fun deleteAll(){
        GlobalScope.launch {
            userDao.deleteAll()
        }
    }
    fun delete(item:DBItem){
        GlobalScope.launch {
            userDao.delete(item)
        }
    }
    fun searchRepo(name:String){
        if(name.isNullOrBlank()){
            getSingle()
        }else{
            userDao.searchRepos(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.setList(it)
                    Log.i("DB","$it")
                },{

                })
        }


    }
}