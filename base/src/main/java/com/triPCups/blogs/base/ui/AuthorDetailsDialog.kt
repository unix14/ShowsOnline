package com.triPCups.blogs.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.common.openUrl
import com.triPCups.blogs.base.custom_views.AuthorView
import com.triPCups.blogs.base.models.Author

class AuthorDetailsDialog: BottomSheetDialogFragment() {

    companion object {
        private const val AUTHOR_DETAILS_TAG = "author"

        fun newInstance(author: Author): AuthorDetailsDialog =
            AuthorDetailsDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(AUTHOR_DETAILS_TAG, author)
                }

            }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.blog_author_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().apply {
            //TODO :: handle deprecation of getSerializable
            getSerializable(AUTHOR_DETAILS_TAG)?.let { author ->
                view.findViewById<AuthorView>(R.id.author_fragment_author_view).setAuthor(author as Author, null)
                view.findViewById<TextView>(R.id.author_fragment_description).text = author.desc
                view.findViewById<TextView>(R.id.author_fragment_link).setOnClickListener {
                    author.link.openUrl(requireContext())
                }
                //todo add email button
            }

            //todo add top 3 posts \ latest 3 posts from this author
        }

    }

}