package com.niloda.github.integration

import kotlinx.serialization.json.Json

/**
 * Transport layer for calling Github API
 * Pulls in the token dependency and performs no de/serialization
 */
interface GithubCaller {
    val githubProperties: GithubProperties
    val json: Json
    val token get() = githubProperties.token

    val webClient: WebClientBuilder
}

