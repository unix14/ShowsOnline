package com.triPCups.blogs.base.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

import android.view.LayoutInflater

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.triPCups.blogs.base.adapters.PostLinksAdapter
import com.triPCups.blogs.base.databinding.LinksViewBinding
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink


class LinksView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var linksAdapter: PostLinksAdapter? = null
    private var binding: LinksViewBinding

    init {
        binding = LinksViewBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    fun setLinks(post: BlogPost, isAdmin: Boolean = false, listener: PostLinksAdapter.PostLinksAdapterListener? = null) = with(binding) {
        val shouldShow = post.hasLinks || isAdmin
        val links = post.links?: arrayListOf()
        postLinksRecycler.apply {
            linksAdapter = PostLinksAdapter(links, isAdmin = isAdmin, listener= listener)
            adapter = linksAdapter
            layoutManager = LinearLayoutManager(context)
            isVisible = shouldShow
        }
        postLinksTitle.isVisible = shouldShow

        postLinksDeleteBtn.apply {
            isVisible = isAdmin
            setOnClickListener {
                linksAdapter?.addEmptyLink()
            }
        }
    }
}