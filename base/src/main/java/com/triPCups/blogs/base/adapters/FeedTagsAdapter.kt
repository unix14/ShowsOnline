package com.triPCups.blogs.base.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.models.PostTag

@SuppressLint("RecyclerView,NotifyDataSetChanged")
class FeedTagsAdapter(
    private val possibleTags: ArrayList<PostTag> = arrayListOf(),
    private val isAdmin: Boolean = false,
    private val onAdminRemoveTag: ((PostTag?) -> Unit)? = null,
    private val onFilterByTag: ((PostTag?) -> Unit)? = null,
) : RecyclerView.Adapter<FeedTagsAdapter.FeedTagViewHolder>() {

    private var selectedTagIndex: Int = -1

    override fun getItemCount(): Int = possibleTags.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedTagViewHolder =
        FeedTagViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.blog_post_tag_item, parent,false))

    override fun onBindViewHolder(holder: FeedTagViewHolder, position: Int) {
        possibleTags[position].let { tag ->
            holder.bind(tag)
        }
    }

    fun submitList(possibleTags: ArrayList<PostTag>) {
        this.possibleTags.apply {
            clear()
            addAll(possibleTags)
            notifyDataSetChanged()
        }
    }

    inner class FeedTagViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val tagText: TextView = v.findViewById(R.id.post_tag)
        private val tagCardView: CardView = v.findViewById(R.id.post_tag_root)

        private var tag: PostTag? = null

        private val isSelected
            get() = selectedTagIndex == adapterPosition

        init {
            tagCardView.setOnClickListener {
                onFilterByTag?.let {
                    if(selectedTagIndex == adapterPosition) {
                        // clear
                        selectedTagIndex = -1
                        onFilterByTag.invoke(null)
                    } else {
                        // select
                        selectedTagIndex = adapterPosition
                        onFilterByTag.invoke(tag)
                    }
                    notifyDataSetChanged()
                } ?: kotlin.run {
                    if(isAdmin) {
                        possibleTags.apply {
                            get(adapterPosition).let { removedTag ->
                                remove(removedTag)
                                notifyItemRemoved(adapterPosition)
                                onAdminRemoveTag?.invoke(removedTag)
                            }
                        }
                    }
                }
            }
        }

        fun bind(tag: PostTag) {
            this.tag = tag
            tagText.text = tag.tag

            tagCardView.isSelected = isSelected
            tagText.isSelected = isSelected
        }
    }
}