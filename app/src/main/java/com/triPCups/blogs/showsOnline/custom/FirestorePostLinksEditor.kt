package com.triPCups.blogs.showsOnline.custom

import com.google.firebase.firestore.FirebaseFirestore
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink
import com.triPCups.blogs.base.repos.links_editor.PostLinksEditor
import com.triPCups.blogs.showsOnline.common.Constants
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestorePostLinksEditor(private val firestore: FirebaseFirestore,): PostLinksEditor {

    override suspend fun onUpdateLinksInPost(post: BlogPost, links: ArrayList<HyperLink>): PostLinksEditor.PostLinksEditorEvents {
        return suspendCoroutine { continuation ->
            post.links = links

            val docRef = firestore.collection(post.categoryName ?: Constants.databaseQAEntryName)
                    .document(post.id ?: "test")
            firestore.runTransaction { transaction ->
                transaction.update(docRef, "links", post.links)
            }.addOnSuccessListener {
                continuation.resume(PostLinksEditor.PostLinksEditorEvents.Success)
            }.addOnFailureListener {
                continuation.resume(PostLinksEditor.PostLinksEditorEvents.Error(it.localizedMessage ?: "Failed sending comment"))
            }
        }
    }
}