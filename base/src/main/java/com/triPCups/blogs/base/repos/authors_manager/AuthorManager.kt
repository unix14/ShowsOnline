package com.triPCups.blogs.base.repos.authors_manager

import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.User
import java.util.ArrayList

interface AuthorManager {

    sealed class AuthorManagerEvents {
        class SearchResult(val author: Author): AuthorManagerEvents()
        class SearchResults(val authors: ArrayList<Author>): AuthorManagerEvents()
        object Success: AuthorManagerEvents()
        class Error(val msg: String): AuthorManagerEvents()
    }

    suspend fun getAuthorByUserId(userId: Int): AuthorManagerEvents
    suspend fun getAuthorByName(name: String): AuthorManagerEvents
    suspend fun getAuthorByUser(user: User): AuthorManagerEvents

    suspend fun addAuthor(author: Author): AuthorManagerEvents
    suspend fun getAllAuthors(): AuthorManagerEvents
    suspend fun deleteAuthor(author: Author): AuthorManagerEvents

}