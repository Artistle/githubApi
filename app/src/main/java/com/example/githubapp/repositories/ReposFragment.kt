package com.example.githubapp.repositories

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.ReposAdapter
import com.example.githubapp.models.RepositoryModel
import com.example.githubapp.utils.showToolbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.util.concurrent.TimeUnit

class ReposFragment:MvpAppCompatFragment(R.layout.repos_fragment),ReposView {
    @InjectPresenter
    lateinit var presenter: ReposPresenter

    lateinit var recycler:RecyclerView

    override fun onStart() {
        super.onStart()
        showToolbar()
        recycler = this.activity?.findViewById(R.id.recycler_repos)!!

//        var searchView = this.activity?.findViewById<SearchView>(R.id.searchView)
//
//        Observable.create(ObservableOnSubscribe<String> { subscriber ->
//            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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
//            .debounce(350, TimeUnit.MILLISECONDS)
//            //.distinct()
//            //.filter { it -> it.isNotBlank()}
//            .subscribe {
//                println("$it")
//                Log.i("OBSERVER","repos: $it")
//            }

    }

    override fun getReposlist(repos: List<RepositoryModel>) {
        recycler.adapter = ReposAdapter(repos as ArrayList<RepositoryModel>)
    }
}