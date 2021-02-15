package com.example.githubapp.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.models.trueModels.Item
import com.example.githubapp.utils.Clickable

//class ReposAdapter(var reposList:ArrayList<Item>): PagedListAdapter<, ReposAdapter.Holder>() {
class ReposAdapter(var reposList:ArrayList<Item>, private val click:Clickable): RecyclerView.Adapter<ReposAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val logo = itemView.findViewById<ImageView>(R.id.image_repos_owner)
        var textReposName = itemView.findViewById<TextView>(R.id.item_repos_name)
        var textReposOwner = itemView.findViewById<TextView>(R.id.item_repos_owner)
        var save = itemView.findViewById<Button>(R.id.button_save_repos)



        fun bind(item:Item,click: Clickable) = with(itemView){

            save.setOnClickListener {
                //callback.clickItem(item)
                click.click(item)
            }

            textReposName.text = item.full_name
            textReposOwner.text = item.owner.login
            Glide
                .with(itemView.context)
                .load("${item.owner.avatar_url}")
                .into(logo)
            return@with
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(

        LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(reposList[position],click)

    override fun getItemCount() = reposList.size
}