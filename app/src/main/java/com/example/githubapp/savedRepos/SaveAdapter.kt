package com.example.githubapp.savedRepos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.database.DBItem
import com.example.githubapp.R
import com.example.githubapp.utilits.ClickDeleteRepo

class SaveAdapter(var reposList:List<DBItem>, private val click: ClickDeleteRepo): RecyclerView.Adapter<SaveAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val avatar = itemView.findViewById<ImageView>(R.id.image_repos_owner)
        private var textReposName = itemView.findViewById<TextView>(R.id.item_repos_name)
        private var textReposOwner = itemView.findViewById<TextView>(R.id.item_repos_owner)

        private var forks = itemView.findViewById<TextView>(R.id.item_repos_forks)
        private var stars = itemView.findViewById<TextView>(R.id.item_repos_stars)
        private var description = itemView.findViewById<TextView>(R.id.item_repos_create_date)
        private var createDate = itemView.findViewById<TextView>(R.id.item_repos_create_date)

        private var save = itemView.findViewById<Button>(R.id.button_save_repos)
        private var cont = itemView.findViewById<LinearLayout>(R.id.container_gone)

        private var btnSave = itemView.findViewById<Button>(R.id.button_save_repos)

        fun bind(item: DBItem, click: ClickDeleteRepo) = with(itemView){

            btnSave.setOnClickListener {
                click.delete(item)
            }

            this.setOnClickListener {
                cont.visibility = if (cont.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
            textReposName.text = item.reposName
            textReposOwner.text = item.fullname
            forks.text = "Forks: ${item.fork.toString()}"
            stars.text = "Stars: ${item.stars.toString()}"
            description.text = item.desc
            createDate.text = "Created: ${item.createdDate}"
//            Glide
//                .with(itemView.context)
//                .load("${item.owner.avatar_url}")
//                .into(avatar)
            return@with
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(

        LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(reposList[position],click)

    override fun getItemCount() = reposList.size
}