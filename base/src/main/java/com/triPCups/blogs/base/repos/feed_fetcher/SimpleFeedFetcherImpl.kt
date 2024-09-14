package com.triPCups.blogs.base.repos.feed_fetcher

import com.triPCups.blogs.base.common.StubData
import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.BlogPost

class SimpleFeedFetcherImpl : FeedFetcher {

    override var cache: BlogFeed? = null

    override suspend fun fetchFeed(dbEntryName: String): BlogFeed {
        if(cache == null) {
            cache = StubData.getBlogFeed()
        }
        return cache!!
    }

    override suspend fun publishFeed(dbEntryName: String, newPosts: ArrayList<BlogPost>)/*: BlogFeed*/ {
        TODO("Not yet implemented")
    }
}