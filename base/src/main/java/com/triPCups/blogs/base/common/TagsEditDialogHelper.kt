package com.triPCups.blogs.base.common

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triPCups.blogs.base.adapters.FeedTagsAdapter
import com.triPCups.blogs.base.databinding.EditTagsDialogViewBinding
import com.triPCups.blogs.base.models.PostTag

object TagsEditDialogHelper {


    fun onClickOpenEditTextDialog(
        context: Context,
        title: String,
        positiveButton: String = "Ok",
//    neutralButton: String = "What",
        negativeButton: String = "Cancel",
        currentTags: ArrayList<PostTag>?,
        onTagsUpdatedCallback: (ArrayList<PostTag>)-> Unit
    ) {
        val alert =  AlertDialog.Builder(context)
        val allTags = currentTags ?: arrayListOf()

        val filterAdapter = FeedTagsAdapter(isAdmin = true, onAdminRemoveTag =  { removedTag ->
            removedTag?.let {
                allTags.remove(it)
                onTagsUpdatedCallback.invoke(allTags)
            }
        })

        val viewBinding: EditTagsDialogViewBinding = EditTagsDialogViewBinding.inflate(
            LayoutInflater.from(context))
        // Set up the input
        val input = viewBinding.tagsDialogEditText
        input.imeOptions = EditorInfo.IME_ACTION_DONE
        input.requestFocus()

        viewBinding.tagsDialogFilterLayout.feedFilterRecycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = filterAdapter
            filterAdapter.submitList(allTags)
        }

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        alert.setView(viewBinding.root)
        alert.setTitle(title)

        alert.setPositiveButton(positiveButton, null)
        alert.setNegativeButton(negativeButton) { dialog, which ->
            run {
                onTagsUpdatedCallback.invoke(allTags)
                dialog.dismiss()
            }
        }
        val dialog = alert.show()
        input.apply {
            postDelayed( {
                openKeyboard()
            }, 340)
        }

        input.doAfterTextChanged {
            if(it?.isEmpty() == true) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).text = "save"
            } else {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).text = "add"
            }
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val newTag = input.text.toString()
            if(newTag.isNotBlank()) {
                allTags.add(PostTag(newTag))
                filterAdapter.submitList(allTags)


                input.text.clear()
            }
            onTagsUpdatedCallback.invoke(allTags)

            if (newTag.isEmpty()) dialog.dismiss()
            //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
        }
    }


}