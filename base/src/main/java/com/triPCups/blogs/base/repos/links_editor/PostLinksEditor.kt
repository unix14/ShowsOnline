package com.triPCups.blogs.base.repos.links_editor

import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink

interface PostLinksEditor {

    sealed class PostLinksEditorEvents {
        object Success: PostLinksEditorEvents()
        class Error(val msg: String): PostLinksEditorEvents()
    }

    suspend fun onUpdateLinksInPost(post: BlogPost, links: ArrayList<HyperLink>) : PostLinksEditorEvents
}