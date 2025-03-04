@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.api

import arrow.core.raise.Raise

interface AddFileCall : GithubCaller {
    context(Raise<Throwable>)
    suspend fun addFile(org: String, repo: String, filename: String, commitMessage: String): String =
        httpClient.put(
            "repos/$org/$repo/contents/$filename",
            commitMessage
        )
}
data class Commit(
    val message: String,
    val comitter: Committer,
    val content: String
) {
    data class Committer(val name: String, val email: String)
}

val commit =            """
{
    "message":"Initial commit",
    "committer":{"name":"Nick Powell","email":"nicholas.powell@gmail.com"},
    "content":"bXkgbmV3IGZpbGUgY29udGVudHM="
}                
            """.trimIndent()