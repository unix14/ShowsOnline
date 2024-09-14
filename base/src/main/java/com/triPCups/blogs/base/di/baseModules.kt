package com.triPCups.blogs.base.di

import com.triPCups.blogs.base.repos.feed_fetcher.FeedFetcher
import com.triPCups.blogs.base.repos.feed_fetcher.FeedFetcherImpl
import com.triPCups.blogs.base.repos.feed_fetcher.SimpleFeedFetcherImpl
import com.triPCups.blogs.base.repos.navigation_manager.NavigationManager
import com.triPCups.blogs.base.repos.navigation_manager.NavigationManagerImpl
import com.triPCups.blogs.base.repos.user_name_generator.AnonNameGeneratorImpl
import com.triPCups.blogs.base.repos.user_name_generator.RandNumberGeneratorImpl
import com.triPCups.blogs.base.repos.user_name_generator.RandomGenerator
import org.koin.dsl.module

val baseModule = module {

    single<FeedFetcher> { getFeedFetcher() }

    single<SimpleFeedFetcherImpl> { getSimpleFeedFetcherImpl() }

    single<NavigationManager> { getNavigationManager() }

    factory<AnonNameGeneratorImpl> { getAnonNameGeneratorImpl() }
    factory<RandNumberGeneratorImpl> { getRandNumberGenerator() }

//    factory<RelevantPostsFetcher> { getRelevantPostsFetcher(get()) }

}

//fun getRelevantPostsFetcher(feedFetcher: FeedFetcher): RelevantPostsFetcher = RelevantPostsFetcherImpl(feedFetcher)

fun getFeedFetcher(): FeedFetcher = FeedFetcherImpl()

fun getSimpleFeedFetcherImpl(): SimpleFeedFetcherImpl = SimpleFeedFetcherImpl()


fun getNavigationManager(): NavigationManager = NavigationManagerImpl()

fun getAnonNameGeneratorImpl(): AnonNameGeneratorImpl = AnonNameGeneratorImpl()

fun getRandNumberGenerator(): RandNumberGeneratorImpl = RandNumberGeneratorImpl()
