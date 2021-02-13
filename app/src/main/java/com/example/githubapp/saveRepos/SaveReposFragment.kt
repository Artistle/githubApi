package com.example.githubapp.saveRepos

import android.util.Log
import androidx.appcompat.widget.SearchView
import com.example.githubapp.R
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.util.concurrent.TimeUnit


class SaveReposFragment:MvpAppCompatFragment(R.layout.fragment_db),SaveReposView {
    @InjectPresenter
    lateinit var presenter: SaveReposPresenter

    override fun onStart() {
        super.onStart()
        var searchView = this.activity?.findViewById<SearchView>(R.id.searchView)

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return false
                }

            })
        })
//            .map { it -> it.toLowerCase() }
            .debounce(350, TimeUnit.MILLISECONDS)
            //.distinct()
            //.filter { it -> it.isNotBlank()}
            .subscribe {
                println("$it")
                Log.i("OBSERVER","save: $it")
            }
    }

}