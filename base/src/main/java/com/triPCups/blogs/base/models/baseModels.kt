package com.triPCups.blogs.base.models

import androidx.navigation.fragment.FragmentNavigator
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

open class BlogPost constructor(
    open var id: String? = null,
    open var title: String? = null,
    open var thumbnail: String? = null,
    open var shortDescription: String? = null,
    open var description: String? = null,
    open var links: ArrayList<HyperLink>? = null,
    open var date: Date? = Date(),
    open var tags: ArrayList<PostTag>? = null,
    open var author: Author = Author(),
    open var comments: CommentSection = CommentSection(/*commentsEnabled = false*/)
) : Serializable {

    open var categoryName: String? = null

    fun isTagged(tag: PostTag): Boolean {
        return if(tags.isNullOrEmpty()) {
            false
        } else {
            var isFound = false
            for(anyTag in tags!!) {
                if(anyTag.tag == tag.tag) {
                    isFound = true
                    break
                }
            }
            isFound
        }
    }

    val hasLinks: Boolean
        get() = links?.isNotEmpty() == true
}



open class User(
    var id: String = "",
    @field:JvmField // use this annotation if your Boolean field is prefixed with 'is'
    val isAdmin: Boolean = false,
    open val name: String = "",
    @field:JvmField
    var isAuthor: Boolean= false
): Serializable {

    fun toAuthor(): Author {
        return Author(name, desc = "", image ="", link="").apply {
            id=this@User.id
        }
    }
}

data class Author constructor(
    override val name: String = "3P Cups",
    val desc: String = "todo todo todo todo todoodo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo todo ",
    val image: String = "https://2.bp.blogspot.com/-S0AxPH-w_3k/XvpIgggPfhI/AAAAAAAABgU/mn2FYpXBUl88obARTFuw8J4Do514-64uwCK4BGAYYCw/s250/ic_logo_splash_tiny.png",
    val link: String = "https://3p-cups.com",
    var showAuthor: Boolean = true,
    var circleImageAuthor: Boolean = false,
    //todo add gravatarId ??
) : User(isAuthor = true) {

}

data class HyperLink(
    var url: String = "",
    var text: String = ""
) : Serializable

open class PostTag constructor(open var tag: String)  : Serializable {
    constructor() : this("")
}

data class BlogFeed constructor(
    var posts: ArrayList<BlogPost> = arrayListOf(),
    val possibleTags: ArrayList<PostTag> = arrayListOf()
)  : Serializable{
}



sealed class NavigationEvents {

    object ShowHomeScreen : NavigationEvents()
    class ShowBlogCategory(val categoryName: String) : NavigationEvents()
    class ShowReadPostScreen(val post: BlogPost, val extras: FragmentNavigator.Extras? = null) : NavigationEvents()
    class ShowEditPostScreen(val post: BlogPost) : NavigationEvents()

    object ShowAboutScreen : NavigationEvents()
    class ShowAuthorDetailsScreen(val author: Author) : NavigationEvents()

    class ShowErrorMessage(val message: String) : NavigationEvents()
}