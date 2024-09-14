package com.triPCups.blogs.showsOnline.ui.edit_post

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink
import com.triPCups.blogs.base.models.PostComment
import com.triPCups.blogs.base.repos.comment_poster.CommentPoster
import com.triPCups.blogs.base.repos.links_editor.PostLinksEditor
import com.triPCups.blogs.base.repos.navigation_manager.NavigationManager
import com.triPCups.blogs.base.repos.post_editor.PostEditor
import com.triPCups.blogs.showsOnline.custom.FirestoreCommentPoster
import com.triPCups.blogs.showsOnline.custom.FirestorePostEditor
import com.triPCups.blogs.showsOnline.custom.FirestorePostLinksEditor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPostViewModel(
    private val postEditor: FirestorePostEditor,
    private val commentPoster: FirestoreCommentPoster,
    private val postLinksEditor: FirestorePostLinksEditor,
    private val navigationManager: NavigationManager
) : ViewModel() {


    fun addOrUpdatePost(post: BlogPost) {
        viewModelScope.launch {
            try {
                val event = postEditor.putPost(post)
                if(event is PostEditor.PostEditorEvents.Success) {
                    navigationManager.goToPost(post)
                } else {
                    val errorMsg = (event as PostEditor.PostEditorEvents.Error).msg
                    navigationManager.showError(errorMsg)
                }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "unknown Error"
                CommentPoster.CommentPosterEvents.Error(errorMsg)
                navigationManager.showError(errorMsg)
            }
        }
    }

    fun deletePost(post: BlogPost) {
        viewModelScope.launch {
            try {
                val event = postEditor.deletePost(post)
                if(event is PostEditor.PostEditorEvents.Success) {
                    if(post.categoryName.isNullOrEmpty()) {
                        navigationManager.goToHome()
                    } else {
                        navigationManager.goToCategory(post.categoryName ?:"")
                    }
                } else {
                    val errorMsg = (event as PostEditor.PostEditorEvents.Error).msg
                    navigationManager.showError(errorMsg)
                }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "unknown Error"
                CommentPoster.CommentPosterEvents.Error(errorMsg)
                navigationManager.showError(errorMsg)
            }
        }
    }

    fun updateCommentOnPost(newPostComment: PostComment, post: BlogPost) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val event = commentPoster.updateCommentOnPost(post, newPostComment)
                if(event is CommentPoster.CommentPosterEvents.Success) {
                    navigationManager.showError("comment updated")
                } else {
                    val errorMsg = (event as CommentPoster.CommentPosterEvents.Error).msg
                    navigationManager.showError(errorMsg)
                }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "unknown Error"
                CommentPoster.CommentPosterEvents.Error(errorMsg)
                navigationManager.showError(errorMsg)
            }
        }
        Log.d("wow", "updateCommentOnPost: ${newPostComment.title}")
    }

    fun deleteCommentFromPost(newPostComment: PostComment, post: BlogPost) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val eventResult = commentPoster.deleteCommentFromPost(post, newPostComment)) {
                is CommentPoster.CommentPosterEvents.Error -> {
                    navigationManager.showError(eventResult.msg)
                }
                is CommentPoster.CommentPosterEvents.Success -> {
                    navigationManager.showError("התגובה נמחקה")
                }
            }
        }
        Log.d("wow", "deleteCommentFromPost: ${newPostComment.title}")
    }

    fun enableCommentsForPost(post: BlogPost, isEnabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val event = commentPoster.enableCommentsForPost(post, isEnabled)
                if(event is CommentPoster.CommentPosterEvents.Success) {
                    navigationManager.showError("comments section is updated")
                } else {
                    val errorMsg = (event as CommentPoster.CommentPosterEvents.Error).msg
                    navigationManager.showError(errorMsg)
                }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "unknown Error"
                CommentPoster.CommentPosterEvents.Error(errorMsg)
                navigationManager.showError(errorMsg)
            }
        }
        Log.d("wow", "enableCommentsForPost: ${post.title}")
    }

    fun onUpdateLinksInPost(post: BlogPost, links: ArrayList<HyperLink>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val event = postLinksEditor.onUpdateLinksInPost(post, links)
                if(event is PostLinksEditor.PostLinksEditorEvents.Success) {
                    navigationManager.showError("Links section is updated")
                } else {
                    val errorMsg = (event as PostLinksEditor.PostLinksEditorEvents.Error).msg
                    navigationManager.showError(errorMsg)
                }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "unknown Error"
                CommentPoster.CommentPosterEvents.Error(errorMsg)
                navigationManager.showError(errorMsg)
            }
        }
        Log.d("wow", "onUpdateLinksInPost: ${post.title}")
    }
}