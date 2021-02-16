package com.example.githubapp.test

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.githubapp.models.reposModel.Item
import com.example.githubapp.models.reposModel.ReposModel

class RepoRxAdapter : PagingDataAdapter<Item,RepoHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        getItem(position)?.let{
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        return RepoHolder.create(parent)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }
}