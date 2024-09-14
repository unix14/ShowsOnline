package com.triPCups.blogs.showsOnline.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triPCups.blogs.base.common.StubData
import com.triPCups.blogs.base.common.StubData.getOldShorts
import com.triPCups.blogs.base.common.StubData.getOldShows
import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.base.repos.navigation_manager.NavigationManager
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.custom.FirestoreFeedFetcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val feedFetcher: FirestoreFeedFetcher,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    val feedLiveData: MutableLiveData<BlogFeed> = MutableLiveData()
    val currentNavigationData: LiveData<NavigationEvents> = navigationManager.currentNavigationData
    val currentCategoryNameData: MutableLiveData<String> =
        MutableLiveData(Constants.databaseEntryName)


    fun fetchFeed(categoryName: String = Constants.databaseEntryName) {
        viewModelScope.launch {
            currentCategoryNameData.postValue(categoryName)

            //todo keep debugging here
//            val feed = getOldShows()

            val feed = feedFetcher.fetchFeed(categoryName)
            feedLiveData.postValue(feed)
        }
    }

    fun importIntoFeed(categoryName: String = Constants.databaseEntryName) {
        viewModelScope.launch {
            val newData = StubData.getStubImport() //todo make sure this is working before using in DEBUG MODE ONLY !!!
            feedFetcher.publishFeed(categoryName, newData.posts)
//            feedLiveData.postValue(feed)
        }
    }
}