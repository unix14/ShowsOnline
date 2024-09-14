package com.triPCups.blogs.base.repos.authors_manager

import com.triPCups.blogs.base.models.Author
import com.triPCups.blogs.base.models.User
import java.util.ArrayList

class AuthorManagerImpl: AuthorManager {

    override suspend fun getAuthorByUserId(userId: Int): AuthorManager.AuthorManagerEvents {
        //TODO implement this
        return AuthorManager.AuthorManagerEvents.Error("Not implemented")
    }

    override suspend fun getAuthorByName(name: String): AuthorManager.AuthorManagerEvents {
        //TODO implement this
        return AuthorManager.AuthorManagerEvents.Error("Not implemented")
    }

    override suspend fun getAuthorByUser(user: User): AuthorManager.AuthorManagerEvents {
        //TODO implement this
        return AuthorManager.AuthorManagerEvents.Error("Not implemented")
    }

    override suspend fun addAuthor(author: Author): AuthorManager.AuthorManagerEvents {
        //TODO implement this
        return AuthorManager.AuthorManagerEvents.Error("Not implemented")
    }

    override suspend fun getAllAuthors(): AuthorManager.AuthorManagerEvents {
        //TODO implement this
        return AuthorManager.AuthorManagerEvents.Error("Not implemented")
    }

    override suspend fun deleteAuthor(author: Author): AuthorManager.AuthorManagerEvents {
        //TODO implement this

        return AuthorManager.AuthorManagerEvents.Error("Not implemented")
    }
}