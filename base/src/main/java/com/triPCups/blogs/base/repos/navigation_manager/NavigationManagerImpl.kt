package com.triPCups.blogs.base.repos.navigation_manager

import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.FragmentNavigator
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.NavigationEvents

class NavigationManagerImpl : NavigationManager {

    override val currentNavigationData = MutableLiveData<NavigationEvents>()

    override fun showError(errorMsg: String) = currentNavigationData.postValue(NavigationEvents.ShowErrorMessage(errorMsg))

    override fun goToHome() = currentNavigationData.postValue(NavigationEvents.ShowHomeScreen)

    override fun goToPost(post: BlogPost, extras: FragmentNavigator.Extras?) =
        currentNavigationData.postValue(NavigationEvents.ShowReadPostScreen(post, extras))


    override fun goToEditPost(post: BlogPost) =
        currentNavigationData.postValue(NavigationEvents.ShowEditPostScreen(post))

    override fun goToAbout() = currentNavigationData.postValue(NavigationEvents.ShowAboutScreen) // todo remove?

    override fun goToAuthorDetails(author: Author) = currentNavigationData.postValue(NavigationEvents.ShowAuthorDetailsScreen(author))


    override fun goToCategory(categoryName: String) = currentNavigationData.postValue(
        NavigationEvents.ShowBlogCategory(categoryName)
    )


}