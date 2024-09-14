package com.triPCups.blogs.showsOnline.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.triPCups.blogs.base.logic.BlogViewModel
import com.triPCups.blogs.base.repos.authors_manager.AuthorManager
import com.triPCups.blogs.base.repos.relevant_posts.RelevantPostsFetcher
import com.triPCups.blogs.base.repos.relevant_posts.FirestoreRelevantPostsFetcher
import com.triPCups.blogs.base.repos.user_name_generator.AnonNameGeneratorImpl
import com.triPCups.blogs.base.repos.user_name_generator.RandNumberGeneratorImpl
import com.triPCups.blogs.showsOnline.custom.FirestoreAuthorManager
import com.triPCups.blogs.showsOnline.custom.FirestoreCommentPoster
import com.triPCups.blogs.showsOnline.custom.FirestoreFeedFetcher
import com.triPCups.blogs.showsOnline.custom.FirestorePostEditor
import com.triPCups.blogs.showsOnline.custom.FirestorePostLinksEditor
import com.triPCups.blogs.showsOnline.ui.invitation_screen.InvitationScreenViewModel
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepository
import com.triPCups.blogs.showsOnline.repos.invitation_system.InvitationsRepositoryImpl
import com.triPCups.blogs.showsOnline.ui.MainViewModel
import com.triPCups.blogs.showsOnline.ui.edit_post.EditPostViewModel
import com.triPCups.blogs.showsOnline.ui.home.HomeViewModel
import com.triPCups.blogs.showsOnline.ui.read_post.ReadPostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { BlogViewModel(get(), get(), get()) }

    viewModel { HomeViewModel(get(), get()) }

    viewModel { ReadPostViewModel(get(), get()) }

    viewModel { EditPostViewModel(get(), get(), get(), get()) }

    viewModel { InvitationScreenViewModel(get()) }

    viewModel { MainViewModel(get()) }




    single<FirestoreFeedFetcher> { getFirestoreFeedFetcher(get(), get()) }
    single<FirestoreCommentPoster> { getFirestoreCommentPoster(get(), get(), get(), get()) }
    single<FirestorePostEditor> { getFirestorePostEditor(get(), get(), get(), get()) }
    single<FirestorePostLinksEditor> { getPostLinksEditor(get()) }
    single<AuthorManager> { getFirestoreAuthorManager(get())}
    single<FirebaseFirestore> { getFirestore() }
//    single<FirebaseApp> { getFirebaseApp(androidContext())}


    single<InvitationsRepository> { InvitationsRepositoryImpl(get(), get()) }



    factory<RelevantPostsFetcher> { getFirestoreRelevantPostsFetcher(get()) }

}

fun getPostLinksEditor(
    fireStore: FirebaseFirestore
): FirestorePostLinksEditor {
    return FirestorePostLinksEditor(fireStore)
}

fun getFirestoreRelevantPostsFetcher(feedFetcher: FirestoreFeedFetcher): RelevantPostsFetcher =
    FirestoreRelevantPostsFetcher(feedFetcher)


//fun getFirebaseApp(context: Context): FirebaseApp = FirebaseApp.initializeApp(context)!!

fun getFirestore(): FirebaseFirestore = Firebase.firestore

fun getFirestoreFeedFetcher(fireStore: FirebaseFirestore, invitationsRepository: InvitationsRepository): FirestoreFeedFetcher =
    FirestoreFeedFetcher(fireStore,invitationsRepository)

fun getFirestoreCommentPoster(fireStore: FirebaseFirestore, anonNameGenerator: AnonNameGeneratorImpl, randNumberGeneratorImpl: RandNumberGeneratorImpl, invitationsRepository: InvitationsRepository): FirestoreCommentPoster =
    FirestoreCommentPoster(fireStore, anonNameGenerator, randNumberGeneratorImpl, invitationsRepository)


fun getFirestorePostEditor(fireStore: FirebaseFirestore, anonNameGenerator: AnonNameGeneratorImpl, invitationsRepository: InvitationsRepository, authorManager: AuthorManager): FirestorePostEditor =
    FirestorePostEditor(fireStore, anonNameGenerator, invitationsRepository, authorManager)

fun getFirestoreAuthorManager(
    fireStore: FirebaseFirestore
): FirestoreAuthorManager =
    FirestoreAuthorManager(fireStore,)