@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.api

import arrow.core.raise.Raise
import com.niloda.github.integration.domain.CreateOrgRepoFullRequest
import kotlinx.serialization.encodeToString

interface CreateRepo: GithubCaller {

    context(Raise<Throwable>)
    suspend fun createOrgRepo(org: String, request: CreateOrgRepoFullRequest): String =
        httpClient.post("orgs/$org/repos",
            json.encodeToString(request)
        )
}

