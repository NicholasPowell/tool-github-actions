package com.niloda.github.integration

import arrow.core.raise.Raise

interface GetRepo: GithubCaller {
    context(Raise<Throwable>)
    suspend fun getRepo(org: String, repo: String): String =
        webClient.get("repos/$org/$repo")
}


