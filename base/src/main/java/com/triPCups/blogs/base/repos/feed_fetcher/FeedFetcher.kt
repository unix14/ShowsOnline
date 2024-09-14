package com.triPCups.blogs.base.repos.feed_fetcher

import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.BlogPost

interface FeedFetcher {

    var cache: BlogFeed?

    suspend fun fetchFeed(dbEntryName: String) : BlogFeed

    suspend fun publishFeed(dbEntryName: String, newPosts: ArrayList<BlogPost>)//: BlogFeed
}