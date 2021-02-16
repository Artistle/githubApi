package com.example.githubapp.authorize

import com.example.githubapp.models.VerificationsModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface AuthorizeView:MvpView {
    @SingleState
    fun checkCode(code: String)
    @SingleState
    fun setTextCode(code:String)
}