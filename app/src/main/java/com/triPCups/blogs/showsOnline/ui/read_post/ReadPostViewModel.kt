package com.triPCups.blogs.showsOnline.ui.read_post

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.base.models.PostComment
import com.triPCups.blogs.base.repos.navigation_manager.NavigationManager
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.custom.FirestoreCommentPoster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadPostViewModel(private val commentPoster: FirestoreCommentPoster,
                        private val navigationManager: NavigationManager
) : ViewModel() {



    fun addCommentToPost(newPostComment: PostComment, post: BlogPost) {
        viewModelScope.launch(Dispatchers.IO) {
            var categoryName = post.categoryName ?: Constants.databaseEntryName
            Log.d("wow", "addComment: categoryName $categoryName")
            navigationManager.currentNavigationData.value?.let { navEvent ->
                Log.d("wow", "addComment: currentNavigationData $navEvent")

                if(navEvent is NavigationEvents.ShowBlogCategory) {
                    categoryName = navEvent.categoryName
                }
            }

            post.categoryName = post.categoryName ?: categoryName ///remove this bad hacks
            commentPoster.sendCommentToPost(post, newPostComment)

        }
        Log.d("wow", "addComment: currentNavigationData ${post.categoryName}")
        Log.d("wow", "addComment: ${newPostComment.author}")
    }


}