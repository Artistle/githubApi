package com.example.githubapp

import android.os.Bundle
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.DI.NavigationModule
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.presenter.MainPresenter
import com.example.githubapp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(),MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = SupportAppNavigator(this,R.id.container)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toothpick.inject(this,Toothpick
            .openScope(AppConstants.APPSCOPE)
            .openSubScope("main")
            .installModules(NavigationModule()))
        navigatorHolder.setNavigator(navigator)
        //presenter.openNewScreen(Screen.AuthFlow)
    }
}