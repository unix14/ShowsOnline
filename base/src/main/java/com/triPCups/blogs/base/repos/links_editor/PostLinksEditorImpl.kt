package com.triPCups.blogs.base.repos.links_editor

import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink

class PostLinksEditorImpl: PostLinksEditor {

    override suspend fun onUpdateLinksInPost(post: BlogPost, links: ArrayList<HyperLink>): PostLinksEditor.PostLinksEditorEvents {
        TODO("Not yet implemented")
    }
}