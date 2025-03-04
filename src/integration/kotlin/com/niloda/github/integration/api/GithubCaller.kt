package com.niloda.github.integration.api

import com.niloda.github.integration.GithubProperties
import com.niloda.github.integration.HttpClientBuilder
import kotlinx.serialization.json.Json

/**
 * Transport layer for calling Github API
 * Pulls in the token dependency and performs no de/serialization
 */
interface GithubCaller {
    val githubProperties: GithubProperties
    val json: Json
    val token get() = githubProperties.token

    val httpClient: HttpClientBuilder
}

