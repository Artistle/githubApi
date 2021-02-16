package com.example.githubapp.savedRepos

import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.database.DBItem
import com.example.githubapp.R
import com.example.githubapp.utilits.ClickDeleteRepo
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class SaveReposFragment:MvpAppCompatFragment(R.layout.fragment_db),SaveReposView,ClickDeleteRepo {
    @InjectPresenter
    lateinit var presenter: SaveReposPresenter

    override fun onStart() {
        super.onStart()
        var searchView = this.activity?.findViewById<SearchView>(R.id.searchView)
        searchView?.setQuery("", false);
        var btnDeleteAll = this?.activity?.findViewById<Button>(R.id.button_delete_all)
        btnDeleteAll?.setOnClickListener {
            presenter.deleteAll()
        }
        searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchRepo(query ?: "")
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchRepo(newText ?: "")
                return false
            }
        })
    }

    override fun setList(items: List<DBItem>) {
        var r = this?.activity?.findViewById<RecyclerView>(R.id.recycler_save)
        r?.adapter = SaveAdapter(items,this)
    }

    override fun delete(item: DBItem) {
        presenter.delete(item)
    }
}