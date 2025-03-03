package com.niloda.github.integration

import arrow.core.raise.Raise

interface AddFileCall : GithubCaller {
    context(Raise<Throwable>)
    suspend fun addFile(org: String, repo: String, filename: String): String =
        webClient.put(
            "repos/$org/$repo/contents/$filename".also { println(it) },
            """
{
    "message":"Initial commit",
    "committer":{"name":"Nick Powell","email":"nicholas.powell@gmail.com"},
    "content":"bXkgbmV3IGZpbGUgY29udGVudHM="
}                
            """.trimIndent()
        )
}