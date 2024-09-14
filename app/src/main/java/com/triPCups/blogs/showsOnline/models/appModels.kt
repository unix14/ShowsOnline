package com.triPCups.blogs.showsOnline.models

import com.triPCups.blogs.base.models.PostTag
import com.triPCups.blogs.showsOnline.common.Constants


//data class QuizBlogPost(
//    val quizUrl: HyperLink,
////    override var title: String?,
////    override var description: String?,
////    override var links: ArrayList<HyperLink>?,
////    override var date: Date?,
////    override val tags: ArrayList<Tags>
//) : BlogPost() {
//
//
//}







sealed class QuizItemTags(override var tag: String) : PostTag() {
    inner class KotlinTag : QuizItemTags(Constants.KOTLIN_TAG)
    inner class CSharpTag : QuizItemTags(Constants.C_SHARP_TAG)
    inner class FlutterTag : QuizItemTags(Constants.DART_TAG)
}