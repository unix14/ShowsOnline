package com.triPCups.blogs.base.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.common.putImage
import com.triPCups.blogs.base.common.putTags
import com.triPCups.blogs.base.common.setDate
import com.triPCups.blogs.base.common.setTextFromHTML
import com.triPCups.blogs.base.custom_views.AuthorView
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostTag

@SuppressLint("NotifyDataSetChanged")
open class BlogFeedAdapter(
    private var feed: BlogFeed = BlogFeed(),
    private val listener: FeedAdapterListener? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val FEED_POST_ITEM_TYPE = 0
        const val FILTER_FEED_ITEM_TYPE = 1
    }

    interface FeedAdapterListener {
        fun onPostClick(post: BlogPost, extras: FragmentNavigator.Extras? = null)
        fun onAuthorClick(author: Author)
        fun onTagClicked(tag: PostTag? = null)
    }

    private var isFilteredList: Boolean = false
    var filteredResults: ArrayList<BlogPost> = feed.posts

    //todo add builder class
    open var shouldShowFilterSection: Boolean = true

    override fun getItemCount(): Int {
        return if (shouldShowFilterSection && isFilteredList) {
            filteredResults.size + 1
        } else {
            if (shouldShowFilterSection) {
                feed.posts.size + 1
            } else {
                feed.posts.size
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && shouldShowFilterSection) {
            FILTER_FEED_ITEM_TYPE
        } else {
            FEED_POST_ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            FILTER_FEED_ITEM_TYPE -> {
                FilterFeedItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.blog_feed_filter_item, parent, false)
                )
            }
            else -> {
                FeedItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.blog_feed_item, parent, false)
                )
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = if (shouldShowFilterSection && isFilteredList) {
            filteredResults
        } else {
            feed.posts
        }
        if (shouldShowFilterSection && position == 0) {
            (holder as FilterFeedItemViewHolder).bind(feed.possibleTags)
        } else {
            list[getPosition(position)].let { post ->
                (holder as FeedItemViewHolder).bind(post)
            }
        }
    }

    private fun getPosition(position: Int) : Int {
        return if(shouldShowFilterSection) {
            position - 1
        } else {
            position
        }
    }

    fun submitList(newFeed: BlogFeed) {
        feed = newFeed
        filteredResults = newFeed.posts
        notifyDataSetChanged()
    }

    fun filterResults(postTag: PostTag?) {
        val newResults: ArrayList<BlogPost> = arrayListOf()
        isFilteredList = if(postTag == null) {
            newResults.addAll(feed.posts)
            false
        } else {
            for (post in feed.posts) {
                if (post.isTagged(postTag)) {
                    newResults.add(post)
                }
            }
            true
        }

        filteredResults = newResults
        notifyDataSetChanged()

        listener?.onTagClicked(postTag)
    }

    fun searchResults(query: String?) {
        isFilteredList = if(query.isNullOrEmpty()) {
            //cancel search
            shouldShowFilterSection = true
            filteredResults = feed.posts
            false
        } else {
            //do search
            shouldShowFilterSection = false
            val newPosts = feed.posts.filter { it.title?.contains(query) == true }
            filteredResults.clear()
            filteredResults.addAll(newPosts)
            true
        }

        notifyDataSetChanged()
    }

    inner class FilterFeedItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val filterRecyclerView: RecyclerView = v.findViewById(R.id.feed_filter_recycler)

        private val filterAdapter = FeedTagsAdapter {
            filterResults(it)
        }

        init {
            filterRecyclerView.layoutManager =
                LinearLayoutManager(v.context, RecyclerView.HORIZONTAL, false)
            filterRecyclerView.adapter = filterAdapter
        }

        fun bind(possibleTags: ArrayList<PostTag>) {
            filterAdapter.submitList(possibleTags/*.shuffled() as ArrayList<PostTag>*/)
        }
    }

    inner class FeedItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val cardLayout: ConstraintLayout = v.findViewById(R.id.feed_item_card_layout)
        private val image: ImageView = v.findViewById(R.id.feed_item_image)
        private val title: TextView = v.findViewById(R.id.feed_item_title)
        private val description: TextView = v.findViewById(R.id.feed_item_description)
        private val tags: TextView = v.findViewById(R.id.feed_item_tags)
        private val authorView: AuthorView = v.findViewById(R.id.feed_item_author_view)
        private val date: TextView = v.findViewById(R.id.feed_item_date)

        private var post: BlogPost? = null

        init {
            cardLayout.setOnClickListener {
                post?.let { blogPost ->
                    listener?.onPostClick(
                        blogPost,
                        generateExtras(blogPost)
                    )
                }
            }
        }

        private fun generateExtras(blogPost: BlogPost): FragmentNavigator.Extras {
            title.transitionName = blogPost.title + "title"
            description.transitionName = blogPost.shortDescription + "desc"
            image.transitionName = blogPost.thumbnail + "image"
            tags.transitionName = if(post?.tags?.isNotEmpty() == true){ post?.tags?.first()?.tag ?: ("Tag" + "Tag") } else ""
            date.transitionName = blogPost.date.toString() + "date"
            val authorImage = authorView.findViewById<ImageView>(R.id.author_view_image)
            authorImage.transitionName = blogPost.author?.name + blogPost.title +"author.name"

            return FragmentNavigatorExtras(
                title to title.transitionName,
                description to description.transitionName,
                image to image.transitionName,
                tags to tags.transitionName,
                authorImage to authorImage.transitionName,
                date to date.transitionName
            )
        }

        fun bind(post: BlogPost) {
            this.post = post
            title.text = post.title
            description.setTextFromHTML(post.shortDescription ?:"")

            tags.putTags(post.tags, shouldShowTags = false)
            image.putImage(post.thumbnail, placeholder = R.drawable.ic_thumbnail, error = R.drawable.ic_thumbnail)
            date.setDate(post.date)
            authorView.setAuthor(post.author) {
                listener?.onAuthorClick(it)
            }
        }
    }
}