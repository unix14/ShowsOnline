package com.triPCups.blogs.showsOnline.custom

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.repos.authors_manager.AuthorManager
import com.triPCups.blogs.base.repos.post_editor.PostEditor
import com.triPCups.blogs.base.repos.user_name_generator.AnonNameGeneratorImpl
import com.triPCups.blogs.showsOnline.BuildConfig
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

class FirestorePostEditor(
    private val firestore: FirebaseFirestore,
    private val anonNameGenerator: AnonNameGeneratorImpl,
    private val invitationsRepository: InvitationsRepository,
    private val authorManager: AuthorManager
) : PostEditor {


    private val isAdmin: Boolean
        get() = invitationsRepository.canAdminAccessData.value == true

//    fun testAdd() {
//        CoroutineScope(Dispatchers.IO).launch {
//            invitationsRepository.currentUser.value?.let {
//                val result = authorManager.addAuthor(it.toAuthor())
//
//                result
//            }
//        }
//    }

    override suspend fun putPost(post: BlogPost): PostEditor.PostEditorEvents {
//        invitationsRepository.currentUser.value?.let { user ->
            if (/*user.isAuthor || user.isAdmin*/invitationsRepository.canAdminAccessData.value == true || BuildConfig.DEBUG) {
                //fetch with coroutine the author object
//                var result: AuthorManager.AuthorManagerEvents = authorManager.getAuthorByUser(user)
//                //if failed to fetch author by user.id we get it by author name
//                if (result is AuthorManager.AuthorManagerEvents.Error) {
//                    result = authorManager.getAuthorByName(user.name)
//                }
//                if (result is AuthorManager.AuthorManagerEvents.SearchResult) {
//                    post.author = result.author
                    val uploadDocumentTo = if (isAdmin) {
                        if (post.categoryName.isNullOrEmpty()) {
                            Constants.databaseEntryName
                        } else {
                            post.categoryName ?: Constants.databaseQAEntryName
                        }
                    } else {
                        post.categoryName ?: Constants.databaseQAEntryName
                    }
                    return suspendCoroutine { continuation ->
                        val collection: CollectionReference = firestore.collection(uploadDocumentTo)
                        (if (post.id.isNullOrEmpty()) {
                            val newDoc = collection.document()
                            val newId = newDoc.id
                            post.id = newId
                            newDoc
                        } else {
                            collection.document(
                                post.id ?: anonNameGenerator.generate()
                                    .plus(Random.nextInt().toString())
                            )
                        }).set(post)
                            .addOnSuccessListener {
                                continuation.resume(PostEditor.PostEditorEvents.Success)
                            }.addOnFailureListener {
                                continuation.resume(PostEditor.PostEditorEvents.Error("Error putting new post"))
                            }
                    }
//                } else {
//                    val errorMsg = (result as AuthorManager.AuthorManagerEvents.Error).msg
//                    return PostEditor.PostEditorEvents.Error(errorMsg)
//                }
//            } else {
//                return PostEditor.PostEditorEvents.Error("Error current user is not admin nor author")
//            }
        }
        return PostEditor.PostEditorEvents.Error("Error current user is null")
    }

    override suspend fun deletePost(post: BlogPost): PostEditor.PostEditorEvents {
        return suspendCoroutine { continuation ->
//            invitationsRepository.currentUser.value?.let { user ->
                if (BuildConfig.DEBUG || invitationsRepository.canAdminAccessData.value == true/*user.isAdmin || post.author.id == user.id*/) {
                    firestore.collection(post.categoryName ?: "").document(post.id ?: "test")
                        .delete().addOnSuccessListener {
                            continuation.resume(PostEditor.PostEditorEvents.Success)
                        }.addOnFailureListener {
                            continuation.resume(PostEditor.PostEditorEvents.Error("Error putting new post"))
                        }
                } else {
                    continuation.resume(PostEditor.PostEditorEvents.Error("Error current user is not admin nor original author"))
                }
//            } ?: kotlin.run {
//                continuation.resume(PostEditor.PostEditorEvents.Error("Error current user is null"))
//            }
        }
    }
}