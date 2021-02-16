package com.example.githubapp.utilits

import android.content.Context
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.example.githubapp.R
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatFragment

fun MvpAppCompatFragment.showMessage(context: Context, message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
fun MvpAppCompatFragment.hideToolbar(){
    var toolbar = activity?.findViewById<TabLayout>(R.id.table_layout)
    var searchView = activity?.findViewById<SearchView>(R.id.search_view)
    searchView?.visibility = View.GONE
    toolbar?.visibility = View.GONE
}
fun MvpAppCompatFragment.showToolbar(){
    var toolbar = activity?.findViewById<TabLayout>(R.id.table_layout)
    var searchView = activity?.findViewById<SearchView>(R.id.search_view)
    searchView?.visibility = View.GONE
    toolbar?.visibility = View.VISIBLE
}