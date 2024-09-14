package com.triPCups.blogs.base.repos.comment_poster

import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostComment

class SimpleCommentPosterImpl : CommentPoster {

    override suspend fun sendCommentToPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents {
        TODO("Not yet implemented")
    }

    override suspend fun updateCommentOnPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCommentFromPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents {
        TODO("Not yet implemented")
    }

    override suspend fun enableCommentsForPost(
        post: BlogPost,
        isEnabled: Boolean
    ): CommentPoster.CommentPosterEvents {
        TODO("Not yet implemented")
    }
}