package com.example.githubapp.utilits

import com.example.githubapp.repositories.ReposFragment
import com.example.githubapp.authorize.AuthorizeFragment
import com.example.githubapp.savedRepos.SaveReposFragment
import com.example.githubapp.test.FragmentRx
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screen {
    object Authorize:SupportAppScreen(){
        override fun getFragment() = AuthorizeFragment()
    }
    object Repos:SupportAppScreen(){
        override fun getFragment() = ReposFragment()
    }

    object SaveRepos:SupportAppScreen(){
        override fun getFragment() = FragmentRx()
    }

//    object SaveRepos:SupportAppScreen(){
//        override fun getFragment() = SaveReposFragment()
//    }
}