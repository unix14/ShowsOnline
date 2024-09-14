package com.triPCups.blogs.base.repos.post_editor

import com.triPCups.blogs.base.models.BlogPost

interface PostEditor {

    sealed class PostEditorEvents {
        object Success: PostEditorEvents()
        class Error(val msg: String): PostEditorEvents()
    }

    suspend fun putPost(post: BlogPost): PostEditorEvents

    suspend fun deletePost(post: BlogPost): PostEditorEvents

}