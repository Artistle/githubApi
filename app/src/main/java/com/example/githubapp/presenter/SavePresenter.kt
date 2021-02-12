package com.example.githubapp.presenter

import com.example.githubapp.system.FlowRouter
import com.example.githubapp.view.MainView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SavePresenter @Inject constructor(
    private val router:FlowRouter):BasePresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onSelectItem(){

    }


}