@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.api

import arrow.core.raise.Raise

interface ListRepos : GithubCaller {

    context(Raise<Throwable>)
    suspend fun listReposForOrg(org: String): String =
        httpClient.get("orgs/$org/repos")

}