package com.triPCups.blogs.showsOnline.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepository

//todo refactor to blog view model??
class MainViewModel(private val invitationsRepository: InvitationsRepository) : ViewModel() {


    val currentSearchData: MutableLiveData<String?> = MutableLiveData(null)

    val isAdmin: Boolean
        get() = invitationsRepository.canAdminAccessData.value == true


    fun grantDeveloperAccess() {
        invitationsRepository.canAdminAccessData.postValue(true)
    }


    fun doSearch(query: String?) {
        currentSearchData.postValue(query)
    }




}