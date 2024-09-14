package com.triPCups.blogs.base.models

import java.util.*
import kotlin.collections.ArrayList

//todo implement comments

data class CommentSection(
    var commentsEnabled: Boolean = true,
    var comments: ArrayList<PostComment>? = arrayListOf()

) {

    fun isCommentsEmpty(): Boolean{
        return comments?.isEmpty() == true
    }

}
const val EMPTY_USER_NAME = "ללא שם"

data class PostComment(
    var id: String? = null,
    var author: String = EMPTY_USER_NAME, // todo create random "name generator // todo use Author model
    var date: Date? = Date(),
    var title: String="Empty title",
    var text: String = "No comment",

    var commentReplies: ArrayList<PostComment>? = arrayListOf()
) {
//    var authorGravatarId: String? = null
}