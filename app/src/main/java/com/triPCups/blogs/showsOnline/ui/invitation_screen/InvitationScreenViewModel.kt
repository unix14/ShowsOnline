package com.triPCups.blogs.showsOnline.ui.invitation_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepository
import com.triPCups.blogs.base.models.User

class InvitationScreenViewModel(private val invitationsRepository: InvitationsRepository): ViewModel() {

    val canAccessData: LiveData<Boolean?> = invitationsRepository.canAccessData
    val currentUserData: LiveData<User?> by lazy {
        invitationsRepository.currentUser
    }

    val uniqueInvitationId: LiveData<String> = MutableLiveData("").apply {
        postValue(invitationsRepository.uniqueInvitationId)
    }

    fun checkAccess() {
        invitationsRepository.checkIfCanAccess()
    }


}