package com.example.githubapp

import com.example.githubapp.repositories.ReposFragment
import com.example.githubapp.authorize.AuthorizeFragment
import com.example.githubapp.saveRepos.SaveReposFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screen {
    object AuthFlow:SupportAppScreen(){
        override fun getFragment() = AuthorizeFragment()
    }
    object Repos:SupportAppScreen(){
        override fun getFragment() = ReposFragment()
    }
    object SaveRepos:SupportAppScreen(){
        override fun getFragment() = SaveReposFragment()
    }
}