package com.triPCups.blogs.showsOnline.repos.invitation_system

import androidx.lifecycle.MutableLiveData
import com.triPCups.blogs.base.models.User

interface InvitationsRepository {
    //todo refactor to base

    val uniqueInvitationId: String

    val canAccessData: MutableLiveData<Boolean?>
    val canAdminAccessData: MutableLiveData<Boolean?>

    val currentUser: MutableLiveData<User?>

    fun checkIfCanAccess()
}