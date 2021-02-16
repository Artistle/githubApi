package com.example.githubapp.test

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.githubapp.models.reposModel.Item
import com.example.githubapp.models.reposModel.ReposModel
import com.example.githubapp.retrofitApi.SearchReposApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class GetRxPagingSource(
    private val service: SearchReposApi,
    private val apiKey:String,
    private val locale: Locale
): RxPagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int,Item>> {

        val position = params.key ?: 1

        return service.getRepositories("")
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it,position) }
    }

    private fun toLoadResult(data: ReposModel,position:Int):LoadResult<Int,Item>{
        return LoadResult.Page(
            data = data.items,
            prevKey = if (position == 1) null else position -1,
            nextKey = if (position == data.total_count) null else position + 1
        )
    }
}