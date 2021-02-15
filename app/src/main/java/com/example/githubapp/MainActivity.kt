package com.example.githubapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.githubapp.DI.AppConstants
import com.example.githubapp.DI.NavigationModule
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.main.MainPresenter
import com.example.githubapp.main.MainView
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = SupportAppNavigator(this,R.id.container)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.tableLayout.visibility = View.GONE
        var searchView = binding.searchView
        binding.tableLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> presenter.replaceRepos()
                    1 -> presenter.replaceFavorites()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        //setSupportActionBar(toolbar)
        //supportActionBar?.hide()
//        Observable.create(ObservableOnSubscribe<String> { subscriber ->
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    subscriber.onNext(query!!)
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    subscriber.onNext(newText!!)
//                    return false
//                }
//
//            })
//        })
////            .map { it -> it.toLowerCase() }
//            .debounce(350,TimeUnit.MILLISECONDS)
//            //.distinct()
//            //.filter { it -> it.isNotBlank()}
//            .subscribe {
//                println("$it")
//            Log.i("OBSERVER","subscribe: $it")
//        }

        Toothpick.inject(this,Toothpick
            .openScope(AppConstants.APPSCOPE)
            .openSubScope("main")
            .installModules(NavigationModule()))
        navigatorHolder.setNavigator(navigator)
        //presenter.openNewScreen(Screen.AuthFlow)
    }
}