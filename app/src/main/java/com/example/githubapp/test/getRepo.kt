package com.example.githubapp.test

import androidx.paging.PagingData
import com.example.githubapp.models.reposModel.Item
import io.reactivex.Flowable

interface getRepo {
    fun getRepo(): Flowable<PagingData<Item>>
}