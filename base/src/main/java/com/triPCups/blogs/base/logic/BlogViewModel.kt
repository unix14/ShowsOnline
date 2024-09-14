package com.triPCups.blogs.base.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigator
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.base.models.PostTag
import com.triPCups.blogs.base.repos.navigation_manager.NavigationManager
import com.triPCups.blogs.base.repos.relevant_posts.RelevantPostsFetcher
import com.triPCups.blogs.base.repos.user_name_generator.AnonNameGeneratorImpl

class BlogViewModel(
    private val navigationManager: NavigationManager,
    private val anonNameGenerator: AnonNameGeneratorImpl, // todo delete
    private val relevantPostsFetcher: RelevantPostsFetcher
) : ViewModel() {

    init {
        showHome()
    }

    val currentNavigationData: LiveData<NavigationEvents> = navigationManager.currentNavigationData
    val relevantPosts: MutableLiveData<ArrayList<BlogPost>> = MutableLiveData()

    fun showHome() {
        navigationManager.goToHome()
    }

    fun showPostScreen(post: BlogPost, extras: FragmentNavigator.Extras? = null) {
//        var categoryName = ""
//        navigationManager.currentNavigationData.value?.let { navEvent ->
//            if(navEvent is NavigationEvents.ShowBlogCategory) {
//                categoryName = navEvent.categoryName
//            }
//        }
//        post.categoryName = categoryName
        navigationManager.goToPost(post, extras)

        relevantPosts.postValue(relevantPostsFetcher.getAllSuggestions(post.tags?: arrayListOf<PostTag>(/*RelevantPostsFetcher.ANY*/), post.author, /*post.id ?:*/ post.title ?: "")) // todo change
    }

    fun showEditPostScreen(post: BlogPost) {
        navigationManager.goToEditPost(post)
    }


    fun showAbout() {
        navigationManager.goToAbout()
    }

    fun showCategory(categoryScreenName: String = "") {
        navigationManager.goToCategory(categoryScreenName)
    }




//    fun generateRandomName() = anonNameGenerator.generate()

    fun showAuthor(author: Author, isFromComments: Boolean = false) {
        navigationManager.goToAuthorDetails(author)
    }

}