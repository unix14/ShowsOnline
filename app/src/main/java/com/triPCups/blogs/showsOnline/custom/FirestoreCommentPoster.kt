package com.triPCups.blogs.showsOnline.custom

import com.google.firebase.firestore.FirebaseFirestore
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.EMPTY_USER_NAME
import com.triPCups.blogs.base.models.PostComment
import com.triPCups.blogs.base.repos.comment_poster.CommentPoster
import com.triPCups.blogs.base.repos.user_name_generator.AnonNameGeneratorImpl
import com.triPCups.blogs.base.repos.user_name_generator.RandNumberGeneratorImpl
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoreCommentPoster(
    private val firestore: FirebaseFirestore,
    private val anonNameGenerator: AnonNameGeneratorImpl,
    private val randNumGenerator: RandNumberGeneratorImpl,
    private val invitationsRepository: InvitationsRepository
) : CommentPoster {


    override suspend fun sendCommentToPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents {
        return suspendCoroutine { continuation ->
            comment.id = randNumGenerator.generate()
            comment.author = if(comment.author == EMPTY_USER_NAME) {
                    invitationsRepository.currentUser.value?.name ?: EMPTY_USER_NAME //anonNameGenerator.generate()
                } else {
                    comment.author
                }
            post.comments.comments?.apply {
                add(comment)
            } ?: kotlin.run {
                post.comments.comments = arrayListOf(comment)
            }

            val docRef = firestore.collection(post.categoryName ?: Constants.databaseQAEntryName).document(post.id ?: "test")
            firestore.runTransaction { transaction ->
                transaction.update(docRef, "comments", post.comments)
            }.addOnSuccessListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Success)
            }.addOnFailureListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Error(it.localizedMessage?: "Failed sending comment"))
            }
        }
    }

    override suspend fun updateCommentOnPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents {
        return suspendCoroutine { continuation ->
//            comment.author = invitationsRepository.currentUser.value?.name ?: anonNameGenerator.generate()
            post.comments.comments?.find { it.id == comment.id }?.apply {
                this.copy(
                    id = comment.id,
                    author= comment.author,
                    date= comment.date,
                    title = comment.title,
                    text = comment.text,
                    commentReplies = comment.commentReplies
                )
//            } ?: kotlin.run {
//                continuation.resume(CommentPoster.CommentPosterEvents.Error("wrong comment id"))
            }

            val docRef = firestore.collection(post.categoryName ?: Constants.databaseQAEntryName).document(post.id ?: "test")
            firestore.runTransaction { transaction ->
                transaction.update(docRef, "comments", post.comments)
            }.addOnSuccessListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Success)
            }.addOnFailureListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Error(it.localizedMessage?: "Failed updating comment"))
            }
        }
    }

    override suspend fun deleteCommentFromPost(
        post: BlogPost,
        comment: PostComment
    ): CommentPoster.CommentPosterEvents {
        return suspendCoroutine { continuation ->
//            comment.author = invitationsRepository.currentUser.value?.name ?: anonNameGenerator.generate()
            var newComments = post.comments.comments
            post.comments.comments?.filter { it.id != comment.id }?.apply {
                newComments = this as ArrayList<PostComment>
                post.comments.comments = newComments
            } ?: kotlin.run {
                continuation.resume(CommentPoster.CommentPosterEvents.Error("wrong comment id"))
            }

            val docRef = firestore.collection(post.categoryName ?: Constants.databaseEntryName).document(post.id ?: "first")
            firestore.runTransaction { transaction ->
                transaction.update(docRef, "comments", post.comments)
            }.addOnSuccessListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Success)
            }.addOnFailureListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Error(it.localizedMessage?: "Failed deleting comment"))
            }
        }
    }

    override suspend fun enableCommentsForPost(
        post: BlogPost,
        isEnabled: Boolean
    ): CommentPoster.CommentPosterEvents {
        return suspendCoroutine { continuation ->
                post.comments.commentsEnabled = isEnabled

            val docRef = firestore.collection(post.categoryName ?: Constants.databaseQAEntryName).document(post.id ?: "test")
            firestore.runTransaction { transaction ->
                transaction.update(docRef, "comments", post.comments)
            }.addOnSuccessListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Success)
            }.addOnFailureListener {
                continuation.resume(CommentPoster.CommentPosterEvents.Error(it.localizedMessage?: "Failed sending comment"))
            }
        }
    }
}