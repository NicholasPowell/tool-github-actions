@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.impl

import arrow.core.raise.Raise
import com.niloda.github.integration.domain.GithubRepository
import com.niloda.github.spi.domain.FetchRepositoryNames
import kotlinx.serialization.json.Json

class GithubFetchRepositoryNames(
    private val json: Json,
    private val githubApiCaller: GithubApiCaller,
    val org: String = "niloda-me"
) : FetchRepositoryNames {
    context(Raise<Throwable>)
    override suspend fun invoke(): List<String> =
        json.decodeFromString<List<GithubRepository>>(
            githubApiCaller.listReposForOrg(org)
        ).map { it.name }
}