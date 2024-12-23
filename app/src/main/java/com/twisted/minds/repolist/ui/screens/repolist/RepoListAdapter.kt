package com.twisted.minds.repolist.ui.screens.repolist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twisted.minds.repolist.R
import com.twisted.minds.repolist.data.model.vo.RepoVO
import com.twisted.minds.repolist.databinding.ItemRepoListBinding
import com.twisted.minds.repolist.utils.FunctionParam

internal class RepoListAdapter(
    val activity: Activity,
    val onClick: FunctionParam<RepoVO>
): RecyclerView.Adapter<RepoListAdapter.RepoListVH>() {

    var list = listOf<RepoVO>()
        set(value) {
            field = value
            notifyItemRangeChanged(
                list.size - PAGE_SIZE - 1,
                PAGE_SIZE
            )
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListVH {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_repo_list, parent, false
        )
        return RepoListVH(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RepoListVH, position: Int) =
        holder.bind(list[position])

    inner class RepoListVH(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemRepoListBinding.bind(view)

        fun bind(item: RepoVO) {
            binding.apply {
                Glide.with(activity)
                    .load(item.authorPicture)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemRepoAuthorPictureIv)

                itemRepoNameTv.text = item.name
                itemRepoNameTv.contentDescription =
                    activity.getString(R.string.repo_list_name_content, item.name)

                itemRepoDescriptionTv.text = item.description
                itemRepoDescriptionTv.contentDescription =
                    activity.getString(R.string.repo_list_description_content, item.description)

                itemRepoAuthorNameTv.text = item.authorName
                itemRepoDescriptionTv.contentDescription =
                    activity.getString(R.string.repo_list_owner_content, item.authorName)

                itemRepoForksTv.text = item.forks
                itemRepoForksTv.contentDescription =
                    activity.getString(R.string.repo_list_fork_content, item.forks)

                itemRepoStarsTv.text = item.stars
                itemRepoStarsTv.contentDescription =
                    activity.getString(R.string.repo_list_star_content, item.stars)

                itemRepoCl.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}
