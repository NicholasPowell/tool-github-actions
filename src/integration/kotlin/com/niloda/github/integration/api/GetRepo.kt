@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.api

import arrow.core.raise.Raise
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

interface GetRepo: GithubCaller {
    context(Raise<Throwable>)
    suspend fun getRepo(org: String, repo: String): String =
        jacksonObjectMapper().readValue<Map<String, Any>>(
            httpClient.get("repos/$org/$repo")
        ).entries.joinToString { it.key }
}


