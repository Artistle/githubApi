package com.example.githubapp.repositories

import com.example.githubapp.models.reposModel.Item
import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.SingleState

interface ReposView:MvpView {
    @OneExecution
    fun getReposlist(repos:List<Item>)

    @SingleState
    fun clickItem(item:Item)
}