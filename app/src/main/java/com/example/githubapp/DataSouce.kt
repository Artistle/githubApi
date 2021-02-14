package com.example.githubapp

import android.content.SharedPreferences
import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.example.githubapp.models.trueModels.ReposModel
import com.example.githubapp.retrofitApi.configurations.SearchConfig
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DataSource: ItemKeyedDataSource<Long,ReposModel>() {
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun getKey(item: ReposModel): Long {

        //пофиксить эту чушь!!!
        return 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<ReposModel>) {
        compositeDisposable.add(SearchConfig.searchTest("f",1).subscribe({
            var list = listOf<ReposModel>(it)
            callback.onResult(list)
            Log.i("FUCK","$it")
        },{

        }))
    }
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<ReposModel>) {
        compositeDisposable.add(SearchConfig.searchTest("",params.key).subscribe({
               //callback.
        },{

        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<ReposModel>) {

    }


}