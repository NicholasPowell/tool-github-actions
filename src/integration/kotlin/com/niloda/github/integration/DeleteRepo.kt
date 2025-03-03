package com.niloda.github.integration

import arrow.core.raise.Raise

interface DeleteRepo: GithubCaller {

    context(Raise<Throwable>)
    suspend fun deleteOrgRepo(org: String, repo: String): Unit =
        webClient.delete("repos/$org/$repo")
}

