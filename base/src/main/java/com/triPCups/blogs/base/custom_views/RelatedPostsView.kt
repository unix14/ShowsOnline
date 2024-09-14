package com.triPCups.blogs.base.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

import android.view.LayoutInflater

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.triPCups.blogs.base.adapters.BlogFeedAdapter
import com.triPCups.blogs.base.databinding.RelatedPostsViewBinding
import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.BlogPost


class RelatedPostsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    private var binding: RelatedPostsViewBinding

    init {

        binding = RelatedPostsViewBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    private fun initView(listener: BlogFeedAdapter.FeedAdapterListener? = null) = with(binding) {
        postRelevantPostsRecycler.apply {
            adapter = BlogFeedAdapter(listener= listener)
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun bindRelevantPosts(relevantPosts: ArrayList<BlogPost>?, listener: BlogFeedAdapter.FeedAdapterListener? = null) = with(binding) {
        initView(listener)

        if (relevantPosts.isNullOrEmpty()) {
            postRelevantPostsRecycler.isVisible = false
            postRelevantPostsTitle.isVisible = false
        } else {
            postRelevantPostsRecycler.isVisible = true
            postRelevantPostsTitle.isVisible = true
            (postRelevantPostsRecycler.adapter as BlogFeedAdapter).submitList(BlogFeed(relevantPosts))
        }
    }
}