package com.example.githubapp.savedRepos

import com.example.githubapp.database.DBItem
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

interface SaveReposView:MvpView {
    @AddToEnd
    fun setList(items:List<DBItem>)
}