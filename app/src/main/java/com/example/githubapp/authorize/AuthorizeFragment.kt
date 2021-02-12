package com.example.githubapp.authorize

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.githubapp.R
import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.retrofitApi.ConfigApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class AuthorizeFragment:MvpAppCompatFragment(R.layout.authorize_fragment),AuthorizeView {

    @InjectPresenter
    lateinit var authorizePresenter: AuthorizePresenter

    override fun onStart() {
        super.onStart()
        var authorizeGithub = this.activity?.findViewById<Button>(R.id.button_github)
        var authorizeGmail = this.activity?.findViewById<Button>(R.id.button_gmail)
        var authorizeGuest = this.activity?.findViewById<Button>(R.id.button_guest)
        authorizeGithub?.setOnClickListener {authorizePresenter.authorizeGithub()}
        authorizeGmail?.setOnClickListener {}
        authorizeGuest?.setOnClickListener {}
//        Log.i("OBSERVER","ee")
//        getObservable().subscribeWith(getObserver())

        Log.i("OBSERVER","onStart")
    }


    init{


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun startCheck(code: VerificationsModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(code.verification_uri))
        println("")
        startActivityForResult(intent,101)
    }

}