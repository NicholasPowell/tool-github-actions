@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.impl

import arrow.core.raise.Raise
import com.niloda.github.integration.domain.CreateOrgRepoFullRequest
import com.niloda.github.spi.domain.CreateRepository

class GithubCreateRepository(
    private val githubApiCaller: GithubApiCaller,
    private val org: String
) : CreateRepository {
    context(Raise<Throwable>)
    override suspend fun invoke(name: String, isTemplate: Boolean) {
        githubApiCaller.createOrgRepo(
            org = org,
            request = CreateOrgRepoFullRequest(
                name = name,
                isTemplate = isTemplate
            )
        )
    }
}