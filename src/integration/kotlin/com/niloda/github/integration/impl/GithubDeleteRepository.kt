@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.impl

import arrow.core.raise.Raise
import com.niloda.github.spi.domain.DeleteRepository

class GithubDeleteRepository(
    private val githubApiCaller: GithubApiCaller,
    private val org: String
) : DeleteRepository {
    context(Raise<Throwable>)
    override suspend fun invoke(name: String) {
        githubApiCaller.deleteOrgRepo(org = org, repo = name)
    }
}