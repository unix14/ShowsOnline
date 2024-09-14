package com.triPCups.blogs.base.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.common.setTextWithLink
import com.triPCups.blogs.base.models.HyperLink

class PostLinksAdapter(private var list: ArrayList<HyperLink> = arrayListOf(), var isAdmin: Boolean = false, var listener: PostLinksAdapterListener? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface PostLinksAdapterListener {

        fun onEditLinks(newLinks: ArrayList<HyperLink>)
    }

    override fun getItemCount(): Int = list.size

    enum class LinkViewType {
        Link, EditLink
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(isAdmin)
            PostEditLinkItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.blog_post_edit_link_item, parent, false))
        else PostLinkItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.blog_post_link_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            LinkViewType.EditLink.ordinal -> {
                holder as PostEditLinkItemViewHolder
                list[position].apply {
                    holder.bind(this)
                }
            }
            LinkViewType.Link.ordinal -> {
                holder as PostLinkItemViewHolder
                list[position].apply {
                    holder.bind(this, position)
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(isAdmin){
            LinkViewType.EditLink.ordinal
        } else  {
            LinkViewType.Link.ordinal
        }
    }

    fun addEmptyLink() {
        list.add(HyperLink())
        notifyItemInserted(list.size)
        listener?.onEditLinks(list)
    }

    inner class PostEditLinkItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val linkText = v.findViewById<EditText>(R.id.post_link_edit_text)
        private val linkUrl = v.findViewById<EditText>(R.id.post_link_edit_link)
        private val deleteBtn = v.findViewById<ImageView>(R.id.post_links_delete_btn)

        private val link: HyperLink
            get() = list[adapterPosition]

        init {
            linkText.doAfterTextChanged {
                link.text = it.toString()
                listener?.onEditLinks(list)
            }
            linkUrl.doAfterTextChanged {
                link.url = it.toString()
                listener?.onEditLinks(list)
            }

            deleteBtn.setOnClickListener {
                list.remove(link)
                notifyItemRemoved(adapterPosition)
                listener?.onEditLinks(list)
            }
        }

        fun bind(hyperLink: HyperLink) {
            linkText.setText(hyperLink.text)
            linkUrl.setText(hyperLink.url)
        }
    }

    inner class PostLinkItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val linkText = v.findViewById<TextView>(R.id.post_link_text)

        fun bind(hyperLink: HyperLink, position: Int) {
            linkText.setTextWithLink(hyperLink, "Link #${position + 1}")
        }
    }
}