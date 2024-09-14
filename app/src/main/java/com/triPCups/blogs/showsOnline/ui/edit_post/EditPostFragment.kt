package com.triPCups.blogs.showsOnline.ui.edit_post

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.triPCups.blogs.base.adapters.PostCommentsAdapter
import com.triPCups.blogs.base.adapters.PostLinksAdapter
import com.triPCups.blogs.base.common.BaseFragment
import com.triPCups.blogs.base.common.TagsEditDialogHelper
import com.triPCups.blogs.base.common.applyToPost
import com.triPCups.blogs.base.common.putImage
import com.triPCups.blogs.base.common.putTags
import com.triPCups.blogs.base.common.setDate
import com.triPCups.blogs.base.common.setOnClickDatePickerDialog
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink
import com.triPCups.blogs.base.models.NavigationEvents
import com.triPCups.blogs.base.models.PostComment
import com.triPCups.blogs.showsOnline.R
import com.triPCups.blogs.showsOnline.databinding.FragmentEditPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditPostFragment: BaseFragment<FragmentEditPostBinding>(),
    PostCommentsAdapter.PostCommentsAdapterListener, PostLinksAdapter.PostLinksAdapterListener {

    private var post: BlogPost? = null
    override lateinit var binding: FragmentEditPostBinding
    private val viewModel by viewModel<EditPostViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentEditPostBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun initObservers() {
        blogViewModel.currentNavigationData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationEvents.ShowEditPostScreen -> {
                    post = it.post
                    bindPost(it.post)
                }
                else -> {
                }
            }
        }
    }

    override fun initUi() = with(binding) {
        setHasOptionsMenu(true)

        //todo add distinction in adapters for admin privelegaes
        postDate.setOnClickDatePickerDialog(requireContext()) { date ->
            post?.date = date
            postDate.setDate(date, isEditPost = true)
        }

        //Handle text changed for EditTexts
        postImageLink.applyToPost {
            post?.thumbnail = it
            postImage.putImage(it, placeholder = com.triPCups.blogs.base.R.drawable.ic_thumbnail, error = com.triPCups.blogs.base.R.drawable.ic_thumbnail)
        }
        postTitle.applyToPost { post?.title = it }
        postShortDescription.applyToPost { post?.shortDescription = it }
        postContent.applyToPost { post?.description = it }
        //todo handle tags and links and comments and image
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_post_admin, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save -> {
                AlertDialog.Builder(requireContext()).setTitle("Publish or Update Post?").setMessage("Are you sure you want to publish or update this post?").setPositiveButton("Publish"
                ) { p0, p1 -> post?.let { viewModel.addOrUpdatePost(it) } }.setNeutralButton("Oops!..", null).show()
                true
            }
            R.id.action_delete -> {
                AlertDialog.Builder(requireContext()).setTitle("Delete Post?").setMessage("Are you sure you want to delete this post?").setPositiveButton("Delete"
                ) { p0, p1 -> post?.let { viewModel.deletePost(it) } }.setNeutralButton("Oops!..", null).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindPost(post: BlogPost) = with(binding) {
        //Handle text and images
        postTitle.setText(post.title)
        postShortDescription.setText(post.shortDescription)
        postContent.setText(post.description)
        postDate.setDate(post.date, isEditPost = true)

        //TODO Handle Image changes
        postImage.putImage(post.thumbnail, placeholder = R.drawable.ic_thumbnail, error = R.drawable.ic_thumbnail)

        //Handle Tags
        postTags.putTags(post.tags, isEditPost = true)
        postTags.setOnClickListener {
            TagsEditDialogHelper.onClickOpenEditTextDialog(
                requireContext(),
                "Tags",
                positiveButton = "Save",
                negativeButton = "Close",
                currentTags = post.tags
            ) {
                post.tags = it
                postTags.putTags(it, isEditPost = true)
            }
        }

        //Handle Links
        postLinks.setLinks(post, isAdmin = true, listener = this@EditPostFragment)

        //Handle Comment section
        postComments.setComments(post.comments, listener = this@EditPostFragment, isAdmin = true)

        //Handle Author
        postAuthor.setAuthor(post.author) {
            onAuthorClick(it)
            //todo changing author from firebase list
        }
    }

    override fun onAdminUpdateComment(updatedPostComment: PostComment, isDelete: Boolean) {
        post?.let { post ->
            if(isDelete) {
                viewModel.deleteCommentFromPost(updatedPostComment, post)
            } else {
                viewModel.updateCommentOnPost(updatedPostComment, post)
            }
        }
    }

    override fun onAdminEnableComments(isEnabled: Boolean) {
        post?.apply {
            viewModel.enableCommentsForPost(this, isEnabled)
        }
    }

    override fun onEditLinks(newLinks: ArrayList<HyperLink>) {
        post?.apply {
            viewModel.onUpdateLinksInPost(this, newLinks)
        }
    }
}