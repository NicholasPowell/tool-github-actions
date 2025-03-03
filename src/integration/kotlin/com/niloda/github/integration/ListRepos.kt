package com.niloda.github.integration

import arrow.core.raise.Raise

interface ListRepos: GithubCaller {

    context(Raise<Throwable>)
    suspend fun listReposForOrg(org: String): String =
        webClient.get("orgs/$org/repos")
}