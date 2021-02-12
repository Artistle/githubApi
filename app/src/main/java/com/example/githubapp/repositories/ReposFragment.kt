package com.example.githubapp.repositories

import com.example.githubapp.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ReposFragment:MvpAppCompatFragment(R.layout.repos_fragment),ReposView {
    @InjectPresenter
    lateinit var presenter: ReposPresenter
}