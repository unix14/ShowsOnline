package com.triPCups.blogs.showsOnline.custom

import com.google.firebase.firestore.FirebaseFirestore
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.User
import com.triPCups.blogs.base.repos.authors_manager.AuthorManager
import com.triPCups.blogs.showsOnline.common.Constants
import java.util.ArrayList
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoreAuthorManager(
    private val firestore: FirebaseFirestore,
) : AuthorManager {


    override suspend fun getAuthorByUserId(userId: Int): AuthorManager.AuthorManagerEvents {
        return suspendCoroutine { continuation->
            firestore.collection(Constants.databaseAuthorsEntryName).document(userId.toString()).get().addOnSuccessListener { result ->
                result.toObject(Author::class.java)?.let { author: Author ->
                    continuation.resume(AuthorManager.AuthorManagerEvents.SearchResult(author))
                } ?: run {
                    continuation.resume(AuthorManager.AuthorManagerEvents.Error("Author is null"))
                }
            }.addOnFailureListener {
                continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to fetch author by user id"))
            }
        }
    }

    override suspend fun getAuthorByName(name: String): AuthorManager.AuthorManagerEvents {
        return suspendCoroutine { continuation ->
            firestore.collection(Constants.databaseAuthorsEntryName).get().addOnSuccessListener { result ->
                val author: List<Author> = result.toObjects(Author::class.java)
                val findAuthor = author.takeWhile { it.name == name }.firstOrNull()
                findAuthor?.let {
                    continuation.resume(AuthorManager.AuthorManagerEvents.SearchResult(it))
                } ?: run {
                    continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to fetch author by user: Authors is empty"))
                }
            }.addOnFailureListener {
                continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to fetch author by user"))
            }
        }
    }

    override suspend fun getAuthorByUser(user: User): AuthorManager.AuthorManagerEvents {
        return suspendCoroutine { continuation ->
            firestore.collection(Constants.databaseAuthorsEntryName).document(user.id).get().addOnSuccessListener { result ->
                result.toObject(Author::class.java)?.let { author: Author ->
                    continuation.resume(AuthorManager.AuthorManagerEvents.SearchResult(author))
                } ?: run {
                    continuation.resume(AuthorManager.AuthorManagerEvents.Error("Author is null"))
                }
            }.addOnFailureListener {
                continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to fetch author by user"))
            }
        }
    }

    override suspend fun addAuthor(author: Author): AuthorManager.AuthorManagerEvents {
        return suspendCoroutine { continuation ->
            firestore.collection(Constants.databaseAuthorsEntryName).document(author.id).set(author).addOnSuccessListener { result ->
                continuation.resume(AuthorManager.AuthorManagerEvents.Success)
            }.addOnFailureListener {
                continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to add new author"))
            }
        }
    }

    override suspend fun getAllAuthors(): AuthorManager.AuthorManagerEvents {
        return suspendCoroutine { continuation ->
            firestore.collection(Constants.databaseAuthorsEntryName).get().addOnSuccessListener { result ->
                val authors: ArrayList<Author> = (result.toObjects(Author::class.java) as? ArrayList<Author>?) ?: arrayListOf()
                continuation.resume(AuthorManager.AuthorManagerEvents.SearchResults(authors))
            }.addOnFailureListener {
                continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to fetch all authors"))
            }
        }
    }

    override suspend fun deleteAuthor(author: Author): AuthorManager.AuthorManagerEvents {
        return suspendCoroutine { continuation ->
            firestore.collection(Constants.databaseAuthorsEntryName).document(author.id).delete().addOnSuccessListener { result ->
                continuation.resume(AuthorManager.AuthorManagerEvents.Success)
            }.addOnFailureListener {
                continuation.resume(AuthorManager.AuthorManagerEvents.Error("Failed to fetch all authors"))
            }
        }
    }

}