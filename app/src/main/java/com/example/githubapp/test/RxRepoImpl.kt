package com.example.githubapp.test

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.githubapp.models.reposModel.Item
import io.reactivex.Flowable

class RxRepoImpl(
    private val pagindSource:GetRxPagingSource
):getRepo {
    override fun getRepo(): Flowable<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagindSource }
            ).flowable
    }
}