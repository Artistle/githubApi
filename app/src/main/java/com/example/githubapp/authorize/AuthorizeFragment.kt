package com.example.githubapp.authorize

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.githubapp.R
import com.example.githubapp.models.VerificationsModel
import com.example.githubapp.utils.hideToolbar
import com.example.githubapp.utils.showMessage
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class AuthorizeFragment:MvpAppCompatFragment(R.layout.authorize_fragment),AuthorizeView {

    @InjectPresenter
    lateinit var authorizePresenter: AuthorizePresenter

    override fun onStart() {
        super.onStart()
        var authorizeGithub = this.activity?.findViewById<Button>(R.id.button_github)
        var authorizeGmail = this.activity?.findViewById<Button>(R.id.button_gmail)
        var authorizeGuest = this.activity?.findViewById<Button>(R.id.button_guest)
        authorizeGithub?.setOnClickListener {authorizePresenter.authorizeGithub()}
        authorizeGmail?.setOnClickListener {showMessage(this.context!!,"в процессе реализации")}
        authorizeGuest?.setOnClickListener {}
        hideToolbar()
//        Log.i("OBSERVER","ee")
//        getObservable().subscribeWith(getObserver())

        Log.i("OBSERVER","onStart")
        authorizePresenter.getToken()
    }



    override fun onResume() {
        super.onResume()
    }

    override fun startCheck(code: VerificationsModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(code.verification_uri))
        startActivityForResult(intent,101)
    }

    override fun setTextCode(code: String) {
       this?.activity?.findViewById<TextView>(R.id.show_code)?.text = code
    }

}