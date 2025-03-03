package com.niloda.github.integration

import arrow.core.raise.Raise
import com.niloda.github.integration.actions.CreateOrgRepoFullRequest
import kotlinx.serialization.encodeToString

interface CreateRepo: GithubCaller {

    context(Raise<Throwable>)
    suspend fun createOrgRepo(org: String, request: CreateOrgRepoFullRequest): String =
        webClient.post("orgs/$org/repos",
            json.encodeToString(request)
        )
}

