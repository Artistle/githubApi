package com.example.githubapp.saveRepos

import com.example.githubapp.DB.DBItem
import com.example.githubapp.models.trueModels.Item
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface SaveReposView:MvpView {
    @SingleState
    fun setList(items:List<DBItem>)
}