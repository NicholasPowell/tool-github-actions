package com.niloda.github.integration.impl

import arrow.core.raise.Raise
import com.niloda.github.integration.api.GithubCaller

interface DeleteRepo: GithubCaller {

    context(Raise<Throwable>)
    suspend fun deleteOrgRepo(org: String, repo: String): Unit =
        httpClient.delete("repos/$org/$repo")
}

