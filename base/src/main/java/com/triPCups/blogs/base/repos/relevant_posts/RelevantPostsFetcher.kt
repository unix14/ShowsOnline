package com.triPCups.blogs.base.repos.relevant_posts

import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostTag

interface RelevantPostsFetcher {


    companion object {
        const val ANY = "ANY"
    }

    fun getPostsByTag(tag: String) : ArrayList<BlogPost>
    fun getPostsByAuthor(author: Author) : ArrayList<BlogPost>
    fun getAllSuggestions(tags: ArrayList<PostTag>, author: Author = Author(), excludeId: String) : ArrayList<BlogPost>
}