package com.example.githubapp.repositories

import com.example.githubapp.models.RepositoryModel
import com.example.githubapp.models.trueModels.Item
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface ReposView:MvpView {
    @SingleState
    fun getReposlist(repos:List<Item>)
}