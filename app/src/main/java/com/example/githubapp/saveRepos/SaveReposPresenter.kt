package com.example.githubapp.saveRepos

import android.util.Log
import com.example.githubapp.DB.DBItem
import com.example.githubapp.DB.UserDao
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.models.trueModels.Item
import com.example.githubapp.models.trueModels.ReposModel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import toothpick.Toothpick
import javax.inject.Inject

@InjectViewState
class SaveReposPresenter:MvpPresenter<SaveReposView>() {

    @Inject
    lateinit var userDao: UserDao

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
}