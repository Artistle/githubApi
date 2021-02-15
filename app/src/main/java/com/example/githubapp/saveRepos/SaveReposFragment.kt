package com.example.githubapp.saveRepos

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.DB.DBItem
import com.example.githubapp.R
import com.example.githubapp.models.trueModels.Item
import com.example.githubapp.utils.Clickable
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class SaveReposFragment:MvpAppCompatFragment(R.layout.fragment_db),SaveReposView,Clickable {
    @InjectPresenter
    lateinit var presenter: SaveReposPresenter

    override fun onStart() {
        super.onStart()
        var searchView = this.activity?.findViewById<SearchView>(R.id.searchView)
    }

    override fun setList(items: List<DBItem>) {
        var r = this?.activity?.findViewById<RecyclerView>(R.id.recycler_save)
        //recycler = this.activity?.findViewById(R.id.recycler_repos)!!

       r?.adapter = SaveAdapter(items,this)
    }

    override fun click(item: Item) {
        Toast.makeText(this?.context,"Click Save + $item",Toast.LENGTH_SHORT).show()
    }

}