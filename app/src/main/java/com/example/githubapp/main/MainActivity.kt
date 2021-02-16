package com.example.githubapp.main

import android.os.Bundle
import android.view.View
import com.example.githubapp.R
import com.example.githubapp.injection.AppConstants
import com.example.githubapp.injection.NavigationModule
import com.example.githubapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject


/*
* с задачей справился не полностью и не совсем хорошо.
* причины: небольшой опыт работы с toothpick,cicerone и github api(потратил не мало времени чтобы с ним разобраться)
* отсутствие свободного времени из-за ненышней работы,основную часть проекта сделал за выходные дни
* не реализовал авторизацю через gmail/гость.
* есть баг с отображением изображения,сейчас указан точный размер imageView
* не реализовал постраничную загрузку,т.к. времени у меня мало,попробовал реализовать с помощью pagination library,но решил от этой идеи отойти
* так-же не стал заморачиваться о UI
* ну и в целом плохое использование toothpick,Rx только базовое использование,
* в двух местах есть GlobalScope,т.к. для тех задач это было самое краткое и простое решение
* не сделал сохранение аватарки пользователя в базе данных
* в целом думаю с задачей не справился должным образом
*
*
*
* */


class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = SupportAppNavigator(this, R.id.container)
    init{
        Toothpick.inject(this,Toothpick
            .openScope(AppConstants.APPSCOPE)
            .openSubScope(AppConstants.NAVIGATION)
            .installModules(NavigationModule()))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tableLayout.visibility = View.GONE
        presenter.initPresenter(this)
        binding.tableLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> presenter.replaceRepos()
                    1 -> presenter.replaceFavorites()
                }}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}})
        navigatorHolder.setNavigator(navigator)
    }
}