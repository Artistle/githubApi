package com.example.githubapp.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<V:MvpView>:MvpPresenter<V>() {

}