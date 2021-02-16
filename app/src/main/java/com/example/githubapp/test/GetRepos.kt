package com.example.githubapp.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.example.githubapp.models.reposModel.Item
import io.reactivex.Flowable

class GetRepos(private val repository:getRepo):ViewModel() {
    fun getRepos():Flowable<PagingData<Item>>{
        return repository
            .getRepo()
            .cachedIn(viewModelScope)
        }
}
