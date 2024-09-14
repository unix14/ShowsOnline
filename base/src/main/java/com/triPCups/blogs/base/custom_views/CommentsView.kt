package com.triPCups.blogs.base.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

import android.view.LayoutInflater

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.triPCups.blogs.base.adapters.PostCommentsAdapter
import com.triPCups.blogs.base.databinding.CommentsViewBinding
import com.triPCups.blogs.base.models.CommentSection
import com.triPCups.blogs.base.models.PostComment


class CommentsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    private var binding: CommentsViewBinding
//    var listener: PostCommentsAdapter.PostCommentsAdapterListener? = null

    init {

        binding = CommentsViewBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    private fun initView(listener: PostCommentsAdapter.PostCommentsAdapterListener? = null) = with(binding) {
        postCommentsRecycler.apply {
            adapter = PostCommentsAdapter(listener = listener)
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun setComments(
        comments: CommentSection,
        listener: PostCommentsAdapter.PostCommentsAdapterListener? = null,
        isAdmin: Boolean = false
    ) = with(binding) {
        initView(listener)

        adminControls.isVisible = isAdmin
        postCommentsAdminCommentsToggle.apply {
            isChecked = comments.commentsEnabled
            setOnCheckedChangeListener { compoundButton, isEnabled ->
                comments.commentsEnabled = isEnabled // todo remove if useless??
                listener?.onAdminEnableComments(isEnabled)
            }
        }
        if (comments.commentsEnabled || isAdmin) {
            postCommentsTitle.isVisible = true
            postCommentsRecycler.isVisible = true
//            if (comments.isCommentsEmpty()) {
//                // show default placeholder -> should be shown from adapter
//            } else {
            //todo add if is admin and add admin panel for comments
                comments.comments?.let { setComments(it, isAdmin) }
//            }
        } else {
            postCommentsTitle.isVisible = false
            postCommentsRecycler.isVisible = false
        }
    }

    private fun setComments(comments: ArrayList<PostComment>, isAdmin: Boolean) = with(binding.postCommentsRecycler) {
        (adapter as PostCommentsAdapter?)?.apply {
            this.isAdmin = isAdmin
            submitList(comments)
        }
    }
}