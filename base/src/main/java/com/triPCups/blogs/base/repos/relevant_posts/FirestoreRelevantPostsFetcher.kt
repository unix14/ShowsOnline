package com.triPCups.blogs.base.repos.relevant_posts

import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostTag
import com.triPCups.blogs.base.repos.feed_fetcher.FeedFetcher

class FirestoreRelevantPostsFetcher(private val feedFetcher: FeedFetcher) : RelevantPostsFetcher {

    private val allPosts: ArrayList<BlogPost> by lazy {
        feedFetcher.cache?.posts ?: arrayListOf()
    }

    override fun getPostsByTag(tag: String): ArrayList<BlogPost> {
        val sameTagList = arrayListOf<BlogPost>()
        allPosts.forEach { post ->
            post.tags?.forEach { anyTag ->
                if (anyTag.tag == tag) {
                    sameTagList.add(post)
                }
            }
        }

        return (sameTagList.distinctBy { it.title } as ArrayList<BlogPost>).apply {
            shuffle()
        }
    }

    override fun getPostsByAuthor(author: Author): ArrayList<BlogPost> {
//        val sameAuthorList = arrayListOf<BlogPost>()
//        allPosts.forEach { post ->
//            if (post.author == author) {
//                sameAuthorList.add(post)
//            }
//        }
        return (allPosts.filter { it.author == author } as ArrayList<BlogPost>).apply {
            shuffle()
        }
    }

    override fun getAllSuggestions(tags: ArrayList<PostTag>, author: Author, excludeId: String): ArrayList<BlogPost> {
        val returnedList = arrayListOf<BlogPost>()
        tags.forEach {
            (getPostsByTag(it.tag).apply {
                addAll(getPostsByAuthor(author))
            }.distinctBy { it.title } as ArrayList<BlogPost>).apply {
                returnedList.addAll(shuffled())
            }
        }
        returnedList.shuffle()
        returnedList.removeAll { it.title == excludeId } // using title instead of id
        return try {
            returnedList.distinctBy { it.title }.takeLast(5) as ArrayList<BlogPost>
        } catch (e: Exception) {
            returnedList
        }
    }


}

