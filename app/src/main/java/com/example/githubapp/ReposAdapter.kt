package com.example.githubapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.models.RepositoryModel

class ReposAdapter(var reposList:ArrayList<RepositoryModel>): RecyclerView.Adapter<ReposAdapter.Holder>() {
    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val logo = itemView.findViewById<ImageView>(R.id.image_repos_owner)
        var textReposName = itemView.findViewById<TextView>(R.id.item_repos_name)
        var textReposOwner = itemView.findViewById<TextView>(R.id.item_repos_owner)
        fun bind(item:RepositoryModel) = with(itemView){
            textReposName.text = item.name
            textReposOwner.text = item.owner.login
//            Glide
//                .with(itemView.context)
//                .load("${item.owner.avatar_url}")
//                .into(logo)
            return@with
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(reposList[position])

    override fun getItemCount() = reposList.size
}