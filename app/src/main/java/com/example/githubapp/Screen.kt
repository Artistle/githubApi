package com.example.githubapp

import com.example.githubapp.repositories.ReposFragment
import com.example.githubapp.authorize.AuthorizeFragment
import com.example.githubapp.fragments.Fr
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screen {
    object AuthFlow:SupportAppScreen(){
        override fun getFragment() = AuthorizeFragment()
    }
    object Item: SupportAppScreen(){
        override fun getFragment() = Fr()
    }
    object Repos:SupportAppScreen(){
        override fun getFragment() = ReposFragment()
    }
    //fun g() = com.example.githubapp.Screens(AuthorizeFragment())





}