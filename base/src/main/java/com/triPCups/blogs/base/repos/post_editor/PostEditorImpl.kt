package com.triPCups.blogs.base.repos.post_editor

import android.util.Log
import com.triPCups.blogs.base.models.BlogPost

class PostEditorImpl : PostEditor {

    override suspend fun putPost(post: BlogPost): PostEditor.PostEditorEvents {
        //IGNORES ALL PUT POST EVENTS
        Log.d("wow", "putPost: postId ${post.title} ")
        return PostEditor.PostEditorEvents.Error("ToDo Implement")
    }

    override suspend fun deletePost(post: BlogPost): PostEditor.PostEditorEvents {
        //IGNORES ALL DELETE POST EVENTS
        Log.d("wow", "deletePost: postId ${post.title} ")
        return PostEditor.PostEditorEvents.Error("ToDo Implement")
    }
}