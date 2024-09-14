package com.triPCups.blogs.base.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.text.HtmlCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.common.GravatarHelper
import com.triPCups.blogs.base.common.setDate
import com.triPCups.blogs.base.custom_views.AuthorView
import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.CommentSection
import com.triPCups.blogs.base.models.PostComment
import java.util.ArrayList

class PostCommentsAdapter(
    private val listener: PostCommentsAdapterListener? = null,
    var isAdmin: Boolean = false
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface PostCommentsAdapterListener {
        open fun onPostCommentAdded(newPostComment: PostComment) {}
        fun onAuthorClick(author: Author)


        open fun onAdminUpdateComment(updatedPostComment: PostComment, isDelete: Boolean = false) {}
        open fun onAdminEnableComments(isEnabled: Boolean) {}
    }

    private val commentSection: CommentSection = CommentSection()
    private var isExpanded = false //todo add expanding mechanism

    enum class CommentItemType {
        NO_COMMENTS, COMMENT, ADD_COMMENT, /*REPLY*/ ADMIN_EDIT_COMMENT
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isAdmin -> {
                CommentItemType.ADMIN_EDIT_COMMENT.ordinal
            }
            commentSection.commentsEnabled -> {
                //todo add expanding mechanism
                when {
                    position < (commentSection.comments?.size ?: 0) -> {
                        CommentItemType.COMMENT.ordinal
                    }
                    position == (commentSection.comments?.size ?: 0) + 1 -> {
                        CommentItemType.ADD_COMMENT.ordinal
                    }
                    else -> {
                        CommentItemType.NO_COMMENTS.ordinal
                    }
                }
            }
            else -> CommentItemType.NO_COMMENTS.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            CommentItemType.COMMENT.ordinal -> {
                PostCommentItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.blog_post_comment_item, parent, false)
                )
            }
            CommentItemType.ADMIN_EDIT_COMMENT.ordinal -> {
                AdminEditCommentItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.blog_post_admin_edit_comment_item, parent, false)
                )
            }
            CommentItemType.ADD_COMMENT.ordinal -> {
                AddCommentItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.blog_post_add_comment_item, parent, false)
                )
            }
//            CommentItemType.REPLY.ordinal -> {
//            }
//            else -> CommentItemType.NO_COMMENTS.ordinal
            else ->
                AddCommentItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.blog_post_add_comment_item, parent, false)
                )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {

            CommentItemType.COMMENT.ordinal -> {
                commentSection.comments?.get(position)?.let { comment ->
                    (holder as PostCommentItemViewHolder).bind(comment)
                }
            }
            CommentItemType.ADMIN_EDIT_COMMENT.ordinal -> {
                commentSection.comments?.get(position)?.let { comment ->
                    (holder as AdminEditCommentItemViewHolder).bind(comment)
                }
            }
            CommentItemType.ADD_COMMENT.ordinal -> {
            }
            else -> CommentItemType.NO_COMMENTS.ordinal

        }
    }

    override fun getItemCount(): Int {
        return when {
            isAdmin -> {
                commentSection.comments?.size ?: 0
            }
            commentSection.commentsEnabled -> {
                //todo add expanding mechanism
                commentSection.comments?.size?.plus(1) ?: 1
            }
            else -> 0
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(comments: ArrayList<PostComment>) {
        commentSection.comments?.apply {
            clear()
            addAll(comments)
            notifyDataSetChanged()
        }
    }


    inner class PostCommentItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val commentAuthor: AuthorView = v.findViewById(R.id.comment_item_author_view)
        private val commentAuthorName: TextView = v.findViewById(R.id.comment_item_author_name)

        private val commentText: TextView = v.findViewById(R.id.comment_item_description)
        private val commentDate: TextView = v.findViewById(R.id.comment_item_date)

        @SuppressLint("SetTextI18n")
        fun bind(comment: PostComment) {
            //todo set author id for each comment
            commentAuthor.setAuthor(
                Author(
                    name = comment.author,
                    circleImageAuthor = true,
                    showAuthor = true,
                    image = GravatarHelper.getImageFor(comment.author)
                )
            ) {
                listener?.onAuthorClick(it)
            }
            commentAuthorName.text = comment.author // HtmlCompat.fromHtml("נשלח על ידי <b>${}</b> בתאריך", 0)


            commentText.text = comment.text
            commentDate.text = comment.date.toString()
            commentDate.setDate(comment.date, shouldHideDate= false)

            //todo implement sub comments adapter for commentReplies list param

            // todo Admin code
            if(isAdmin) {

            } else {

            }
        }

    }

    inner class AdminEditCommentItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var currentComment: PostComment? = null

        private val commentName: EditText = v.findViewById(R.id.comment_item_edit_name)
//        private val commentTitle: EditText = v.findViewById(R.id.comment_item_edit_title)
        private val commentDescription: EditText = v.findViewById(R.id.comment_item_edit_description)
        private val updateCommentBtn: TextView = v.findViewById(R.id.comment_item_update_comment_btn)
        private val deleteCommentBtn: ImageView = v.findViewById(R.id.comment_item_update_delete_btn)


        //todo add edit author capabilities - with firebase updating of course

        init {
            updateCommentBtn.setOnClickListener {
                if(commentDescription.text.isNotEmpty()) {
                    updateCommentBtn.visibility = View.VISIBLE
                    updateCommentBtn.text = v.context.getString(R.string.comment_updated)
                    //todo add delete button
                    currentComment?.apply {
                        listener?.onAdminUpdateComment(this)
                    }
                } else {
                    Toast.makeText(v.context, v.context.getString(R.string.empty_comment), Toast.LENGTH_SHORT).show()
                }                //todo handle errors
            }
            deleteCommentBtn.setOnClickListener {
                //todo handle errors
                currentComment?.apply {
                    listener?.onAdminUpdateComment(this, isDelete = true)
                }
            }

            commentDescription.doAfterTextChanged {
                currentComment?.apply {
                    text = it.toString()
                }
            }
            commentName.doAfterTextChanged {
                currentComment?.apply {
                    author = it.toString()
                }
            }
            //todo update date
            //todo update author mechanism (from firestore list)
        }

        fun bind(comment: PostComment) {
            currentComment = comment
            commentDescription.setText(comment.text)
        }
    }

    inner class AddCommentItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val commentName: EditText = v.findViewById(R.id.comment_item_edit_name)
        private val commentDescription: EditText = v.findViewById(R.id.comment_item_edit_description)
        private val commentEditTexts: Group = v.findViewById(R.id.comment_item_edit_texts)
        private val sendCommentBtn: TextView = v.findViewById(R.id.comment_item_send_comment_btn)

        init {
            sendCommentBtn.setOnClickListener {
                if(commentName.text.isNotEmpty() && /*commentTitle.text.isNotEmpty() &&*/commentDescription.text.isNotEmpty()) {
                    listener?.onPostCommentAdded(PostComment(
                        author = commentName.text.toString(),
//                        title= commentTitle.text.toString(),
                        text = commentDescription.text.toString(),
                    ))
                    commentEditTexts.visibility = View.GONE
//                    addCommentBtn.visibility = View.VISIBLE
                    sendCommentBtn.visibility = View.VISIBLE
//                    addCommentBtn.text = "Comment Sent, Thanks"
                    sendCommentBtn.text = v.context.getString(R.string.comment_sent)
                    sendCommentBtn.isClickable = false
                    sendCommentBtn.isEnabled = false
                    
//                    commentTitle.text.clear()
                    commentDescription.text.clear()
                } else {
                    Toast.makeText(v.context, v.context.getString(R.string.empty_comment), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}