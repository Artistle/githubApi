package com.example.githubapp.repositories

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.models.trueModels.Item
import com.example.githubapp.utils.Clickable
import com.example.githubapp.utils.showToolbar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ReposFragment:MvpAppCompatFragment(R.layout.repos_fragment),ReposView,Clickable {
    @InjectPresenter
    lateinit var presenter: ReposPresenter
    private lateinit var recycler:RecyclerView
    override fun onStart() {
        super.onStart()
        showToolbar()

        //var save = this.activity?.findViewById<Button>(R.id.button_save_repos)


        val searchView = this.activity?.findViewById<SearchView>(R.id.searchView)
        searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.setRequest(query)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.setRequest(newText)
                return false
            }
        })
    }
    override fun getReposlist(repos: List<Item>) {
        recycler = this.activity?.findViewById(R.id.recycler_repos)!!
        recycler.adapter = ReposAdapter(repos as ArrayList<Item>,this)
    }

    override fun clickItem(item: Item) {
        presenter.saveRepo(item)
        Log.i("OBSERVER","${item.full_name}")
    }

    override fun click(item: Item) {
        presenter.saveRepo(item)
    }
}