package com.triPCups.blogs.base.common

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.viewbinding.ViewBinding
import com.triPCups.blogs.base.adapters.BlogFeedAdapter
import com.triPCups.blogs.base.logic.BlogViewModel
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.base.models.PostTag
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<VB : ViewBinding> : Fragment(), BlogFeedAdapter.FeedAdapterListener {

    abstract var binding: VB

    protected val blogViewModel by sharedViewModel<BlogViewModel>()

    abstract fun initObservers()
    abstract fun initUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moveTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        val fadeTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade)

        sharedElementEnterTransition = moveTransition
        sharedElementReturnTransition = moveTransition

        enterTransition = fadeTransition
        exitTransition = fadeTransition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initUi()
    }

    override fun onPostClick(post: BlogPost, extras: FragmentNavigator.Extras?) {
        blogViewModel.showPostScreen(post, extras)
    }

    override fun onAuthorClick(author: Author) {
        blogViewModel.showAuthor(author)
    }

    override fun onTagClicked(tag: PostTag?) {
        //no need -> handled in Adapter, can be used for analytics
    }

}