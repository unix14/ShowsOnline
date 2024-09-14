package com.triPCups.blogs.showsOnline.custom

import android.util.Log
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.PostTag
import com.triPCups.blogs.base.repos.feed_fetcher.FeedFetcher
import com.triPCups.blogs.showsOnline.common.Constants
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepository
import kotlinx.coroutines.tasks.await
import java.util.UUID
import kotlin.coroutines.suspendCoroutine


class FirestoreFeedFetcher(private val firestore: FirebaseFirestore,
                           private val invitationsRepository: InvitationsRepository
    ) : FeedFetcher {


    override var cache: BlogFeed? = null

    private val isAdmin
        get() = invitationsRepository.canAdminAccessData.value == true

    override suspend fun fetchFeed(dbEntryName: String): BlogFeed {
        Log.d("wow", "fetchFeed: dbEntryName is $dbEntryName isAdmin is $isAdmin")
        //in this If statement we validate that if cache is exists and the article(s) from there are the same name -> we can fetch the cache instead of making a network call
        //also in case of admin, we always want to refresh
        if(cache != null && cache?.posts?.firstOrNull()?.categoryName == dbEntryName && !isAdmin) return cache!!
        return suspendCoroutine { continuation ->
            var blogFeed: BlogFeed?
            firestore.collection(dbEntryName)
                .get()
                .addOnSuccessListener { result ->
                    val blogPosts: List<BlogPost> = result.documents.map { documentSnapshot ->
                        val blogPost = documentSnapshot.toObject(BlogPost::class.java)
                        blogPost?.id = documentSnapshot.id
                        blogPost?.categoryName = dbEntryName
                        blogPost
                    }.filterNotNull()

                    blogFeed = BlogFeed().apply {
                        val postTags: ArrayList<PostTag> = arrayListOf()
                        postTags.addAll(findTagsForPosts(blogPosts))
                        this.possibleTags.apply {
                            clear()
                            addAll(postTags)
                            shuffle()
                        }
                        this.posts.apply {
                            clear()
                            addAll(blogPosts.sortedByDescending { it.date })
                            forEach {
                                Log.d("wow", "getFromFirebase: post id is ${it.id}")
                            }
                            shuffle()
                        }
                    }
                    blogFeed?.let {
                        if(dbEntryName != Constants.databaseQAEntryName) {
                            cache = it
                        }
                        continuation.resumeWith(Result.success(it))
                    } ?: run {
                        continuation.resumeWith(Result.failure(Throwable("blog feed is null")))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("wow", "Error getting documents.", exception)
                    //todo handle errors
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    private fun findTagsForPosts(blogPosts: List<BlogPost>): Collection<PostTag> {
        val setOfTags = hashSetOf<PostTag>()
        for(post in blogPosts) {
            post.tags?.let { setOfTags.addAll(it) }
        }
        return setOfTags.distinctBy { it.tag }
    }

    override suspend fun publishFeed(dbEntryName: String, newPosts: ArrayList<BlogPost>){
        return suspendCoroutine { continuation ->

            newPosts.forEach {
                firestore.collection(dbEntryName)
                    .document(UUID.randomUUID().toString())
                    .set(it)
            }
//                firestore.collection(dbEntryName)
//                .get()
//                .addOnSuccessListener { result ->
//                    val blogPosts: List<BlogPost> = result.toObjects(BlogPost::class.java)
//                    blogFeed = BlogFeed().apply {
////                        (blogPosts as ArrayList<BlogPost>)
//
//                        this.posts.apply {
//                            clear()
//                            addAll(newPosts)
//                            addAll(blogPosts)
//                            forEach {
//                                Log.d("wow", "getFromFirebase: post id is ${it.id}")
//                            }
//                        }
//                        val postTags: ArrayList<PostTag> = arrayListOf()
//                        postTags.addAll(findTagsForPosts(blogPosts))
//                        this.possibleTags.apply {
//                            clear()
//                            addAll(postTags)
//                        }
//                    }
//                    blogFeed?.let {
//                        it.
//                        firestore.collection(dbEntryName)
//                            .add()
//                            .await()
//                        continuation.resumeWith(Result.success(it))
//                    } ?: run {
//                        continuation.resumeWith(Result.failure(Throwable("blog feed is null")))
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.w("wow", "Error getting documents.", exception)
//                    //todo handle errors
//                    continuation.resumeWith(Result.failure(exception))
//                }
        }
    }
}