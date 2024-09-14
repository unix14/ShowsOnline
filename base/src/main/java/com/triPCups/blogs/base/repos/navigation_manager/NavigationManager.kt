package com.triPCups.blogs.base.repos.navigation_manager

import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.FragmentNavigator
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.NavigationEvents

interface NavigationManager {

    val currentNavigationData: MutableLiveData<NavigationEvents>

    fun showError(errorMsg: String)
    fun goToHome()
    fun goToPost(post: BlogPost, extras: FragmentNavigator.Extras? = null)
    fun goToEditPost(post: BlogPost)
    fun goToCategory(categoryName: String)
    fun goToAbout()
    fun goToAuthorDetails(author: Author)
}

