package com.example.githubapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.models.trueModels.ReposModel

class CullAdapter():
    PagedListAdapter<ReposModel,RecyclerView.ViewHolder>(ReposModelDiffCallback) {


    class Holder(view: View):RecyclerView.ViewHolder(view){
        fun bindTo(item:ReposModel?){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReposAdapter.Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as Holder).bindTo(getItem(position))


    companion object{
        val ReposModelDiffCallback = object : DiffUtil.ItemCallback<ReposModel>(){
            override fun areItemsTheSame(oldItem: ReposModel, newItem: ReposModel): Boolean {
                return oldItem.items == newItem.items
            }

            override fun areContentsTheSame(oldItem: ReposModel, newItem: ReposModel): Boolean {
                return oldItem.items == newItem.items
            }

        }
    }
}