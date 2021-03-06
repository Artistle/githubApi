package com.example.githubapp.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.models.reposModel.Item
import com.example.githubapp.utilits.ClickSaveRepo

//class ReposAdapter(var reposList:ArrayList<Item>): PagedListAdapter<, ReposAdapter.Holder>() {
class ReposAdapter(var reposList: ArrayList<Item>, private val click: ClickSaveRepo): RecyclerView.Adapter<ReposAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val avatar: ImageView = itemView.findViewById<ImageView>(R.id.image_repos_owner)
        private var textReposName = itemView.findViewById<TextView>(R.id.item_repos_name)
        private var textReposOwner = itemView.findViewById<TextView>(R.id.item_repos_owner)

        private var forks = itemView.findViewById<TextView>(R.id.item_repos_forks)
        private var stars = itemView.findViewById<TextView>(R.id.item_repos_stars)
        private var description = itemView.findViewById<TextView>(R.id.item_repos_create_date)
        private var createDate = itemView.findViewById<TextView>(R.id.item_repos_create_date)

        private var save = itemView.findViewById<Button>(R.id.button_save_repos)
        private var cont = itemView.findViewById<LinearLayout>(R.id.container_gone)

        fun bind(item: Item, click: ClickSaveRepo) = with(itemView){

            save.setOnClickListener {
                click.click(item)
            }

            this.setOnClickListener {
                cont.visibility = if (cont.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
            textReposName.text = item.full_name
            textReposOwner.text = item.owner.login
            forks.text = "Forks: ${item.forks_count.toString()}"
            stars.text = "Stars: ${item.stargazers_count.toString()}"
            description.text = item.description
            createDate.text = "Created: ${item.created_at}"

            /*
            * пофиксить размер image view
            * */
            Glide
                .with(itemView.context)
                .load("${item.owner.avatar_url}")
                .into(avatar)
            return@with
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(
        reposList[position],
        click
    )

    override fun getItemCount() = reposList.size
}