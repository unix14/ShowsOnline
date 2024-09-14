package com.triPCups.blogs.showsOnline.ui.read_post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.triPCups.blogs.base.adapters.BlogFeedAdapter
import com.triPCups.blogs.base.adapters.PostCommentsAdapter
import com.triPCups.blogs.base.common.BaseFragment
import com.triPCups.blogs.base.common.putImage
import com.triPCups.blogs.base.common.putTags
import com.triPCups.blogs.base.common.setDate
import com.triPCups.blogs.base.common.setTextFromHTML
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.base.models.PostComment
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.common.shareShow
import com.triPCups.blogs.showsOnline.databinding.FragmentReadPostBinding
import com.triPCups.blogs.showsOnline.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReadPostFragment : BaseFragment<FragmentReadPostBinding>(),
    BlogFeedAdapter.FeedAdapterListener, PostCommentsAdapter.PostCommentsAdapterListener {

    private val viewModel by viewModel<ReadPostViewModel>()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override lateinit var binding: FragmentReadPostBinding
    private lateinit var post: BlogPost

//    val args: ReadPostFragmentArgs by navArgs()

//    enum class ConstantsPostsTypes {
//        DYNAMIC, OLD_SHOWS
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentReadPostBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
//        args.readPostFromMenuParam?.let {
//            bindPost(it)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
//        menu.setGroupVisible(R.id.icon_group, true)
        if(getIsAdmin()){
            inflater.inflate(R.menu.show_post_admin, menu)
        } else {
            inflater.inflate(R.menu.main, menu)
        }
    }

    private fun getIsAdmin(): Boolean = mainViewModel.isAdmin

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_edit -> {
                //enter edit mode
                blogViewModel.showEditPostScreen(post)
                true
            }
            R.id.share_post -> {
                post.shareShow(requireContext())
                true
            }
//            R.id.action_new -> {
//                //open new empty post creation fragment
//                true
//            }
//            R.id.action_delete -> {
//                //viewModel. delete after dialog was asked and convinced user to delete
//                viewModel.deletePost(post)
//
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initObservers() = with(viewModel) {
        blogViewModel.relevantPosts.observe(viewLifecycleOwner) {
            bindRelevantPosts(it)
        }
        blogViewModel.currentNavigationData.observe(viewLifecycleOwner) {
            Log.d("wow", "initObservers: event is $it")
            when (it) {
                is NavigationEvents.ShowReadPostScreen -> {
                    bindPost(it.post)
                }
                else -> {
                }
            }
        }
    }

    private fun bindRelevantPosts(posts: java.util.ArrayList<BlogPost>?) = with(binding) {
        postRelatedPosts.bindRelevantPosts(posts, listener = this@ReadPostFragment)
    }

    override fun initUi() {

    }

    private fun bindPost(post: BlogPost) = with(binding) {
        this@ReadPostFragment.post = post
        //Handle text and images
        postTitle.text = post.title
        postShortDescription.setTextFromHTML(post.shortDescription ?: "")
        postContent.setTextFromHTML(post.description ?:"")
        postImage.putImage(post.thumbnail, placeholder = R.drawable.ic_thumbnail, error = R.drawable.ic_thumbnail)
        postTags.putTags(post.tags)
        postDate.setDate(post.date)

        //Handle Links
        postLinks.setLinks(post)

        //Handle Comment section
        postComments.setComments(post.comments, this@ReadPostFragment)

        //Handle Author
        postAuthor.setAuthor(post.author) {
            onAuthorClick(it)
        }

        animateUi(post)
    }

    private fun animateUi(post: BlogPost) = with(binding) {
        postTitle.transitionName = post.title + "title"
        postContent.transitionName = post.shortDescription + "desc"
        postImage.transitionName = post.thumbnail + "image"
        postTags.transitionName = if(post.tags?.isNotEmpty() == true){ post.tags?.first()?.tag ?: ("Tag" + "Tag") } else ""
        postAuthor.findViewById<ImageView>(R.id.author_view_image).transitionName =
            post.author.name + post.title + "author.name"
        postDate.transitionName = post.date.toString() + "date"
    }

    override fun onPostCommentAdded(newPostComment: PostComment) {
        viewModel.addCommentToPost(newPostComment, post)
    }

}