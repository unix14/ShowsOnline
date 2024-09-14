package com.triPCups.blogs.showsOnline.repos.invitation_system

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.common.DeviceUtils
import com.triPCups.blogs.base.models.User
import com.triPCups.blogs.showsOnline.BuildConfig

class InvitationsRepositoryImpl(private val fireStore: FirebaseFirestore, private val ctx: Context) :
    InvitationsRepository {

    override val uniqueInvitationId: String
        get() = DeviceUtils.getUniqueUserID(ctx)

    override val canAccessData: MutableLiveData<Boolean?> = MutableLiveData(null)
    override val currentUser: MutableLiveData<User?> = MutableLiveData(null)

    override val canAdminAccessData: MutableLiveData<Boolean?> = MutableLiveData(null)

    override fun checkIfCanAccess() {
        fireStore.collection(Constants.databaseUsersEntryName)
            .addSnapshotListener { value, error ->
                val users: List<User> = value?.toObjects(User::class.java) ?: listOf()
                if(users.isNotEmpty()) {
                    val possibleUser = users.find { it.id == uniqueInvitationId }
                    Log.d("wow", "checkIfCanAccess: uniqueInvitationId ${uniqueInvitationId}")
                    Log.d("wow", "checkIfCanAccess: error.message ${error?.message}")
                    Log.e("wow", "checkIfCanAccess: error.stackTrace ${error?.stackTrace}")
                    Log.e("wow", "checkIfCanAccess: error.code ${error?.code}")
                    Log.e("wow", "checkIfCanAccess: users ${users.size}")
                    Log.e("wow", "checkIfCanAccess: value ${value?.documents ?: -22}")

                    canAccessData.postValue(possibleUser != null)
                    currentUser.postValue(possibleUser)
                    canAdminAccessData.postValue(possibleUser?.isAdmin == true)
                } else {
                    canAccessData.postValue(true)
                    val isDebug = BuildConfig.DEBUG
                    val anyRandomUser = User(isAuthor = isDebug, isAdmin = isDebug)
                    currentUser.postValue(anyRandomUser)
                    canAdminAccessData.postValue(anyRandomUser.isAdmin)
                }

//                if(error != null) {
                // TODO: 26/04/2022  handle errros
//                    error.message
//                }
            }
    }

}