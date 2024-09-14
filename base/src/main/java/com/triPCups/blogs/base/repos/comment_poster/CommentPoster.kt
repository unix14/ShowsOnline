package com.triPCups.blogs.base.repos.comment_poster

import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostComment

interface CommentPoster {



    sealed class CommentPosterEvents {
        object Success: CommentPosterEvents()
        class Error(val msg: String): CommentPosterEvents()
    }

    suspend fun sendCommentToPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPosterEvents


    suspend fun updateCommentOnPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents


    suspend fun deleteCommentFromPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents


    suspend fun enableCommentsForPost(
        post: BlogPost,
        isEnabled: Boolean
    ): CommentPoster.CommentPosterEvents
}