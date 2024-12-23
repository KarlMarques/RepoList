package com.twisted.minds.repolist.ui.screens.pulllist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twisted.minds.repolist.R
import com.twisted.minds.repolist.data.model.vo.PullVO
import com.twisted.minds.repolist.databinding.ItemPullListBinding
import com.twisted.minds.repolist.extension.dateFormatter

internal class PullListAdapter(
    val activity: Activity
): RecyclerView.Adapter<PullListAdapter.PullListVH>() {

    var list = listOf<PullVO>()
        set(value) {
            field = value
            notifyItemRangeChanged(
                0,
                list.size
            )
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullListVH {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pull_list, parent, false
        )
        return PullListVH(itemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PullListVH, position: Int) =
        holder.bind(list[position])

    inner class PullListVH(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemPullListBinding.bind(view)

        fun bind(item: PullVO) {
            binding.apply {
                Glide.with(activity)
                    .load(item.userPicture)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemPullAuthorPictureIv)

                itemPullTitleTv.text = item.title
                itemPullTitleTv.contentDescription =
                    activity.getString(R.string.pull_list_name_content, item.title)

                itemPullDateTv.text = item.date
                itemPullDateTv.contentDescription =
                    activity.getString(R.string.pull_list_date_content, item.date)

                itemPullBodyTv.text = item.body
                itemPullBodyTv.contentDescription =
                    activity.getString(R.string.pull_list_description_content, item.body)

                itemPullAuthorNameTv.text = item.userName
                itemPullAuthorNameTv.contentDescription =
                    activity.getString(R.string.pull_list_author_content, item.userName)
            }
        }
    }
}
