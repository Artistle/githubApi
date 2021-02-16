package com.example.githubapp.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.models.reposModel.Item

class RepoHolder(private val binding: ActivityMainBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item:Item){
        with(item){
            
        }
    }

    companion object{
        fun create(parent:ViewGroup): RepoHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repos,parent,false)
            val binding = ActivityMainBinding.bind(view)

            return RepoHolder(
                binding
            )
        }
    }

}