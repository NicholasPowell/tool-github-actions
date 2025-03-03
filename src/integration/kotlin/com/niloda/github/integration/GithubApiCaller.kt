package com.niloda.github.integration

import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GithubApiCaller(
    override val githubProperties: GithubProperties,
    override val json: Json
) : GithubCaller,
    ListRepos,
    CreateRepo,
    CreateRepoFromTemplate,
    GetRepo,
    DeleteRepo,
    AddFileCall {

    override val webClient: WebClientBuilder
        get() = webClientInit

    private val webClientInit = WebClientBuilder(
        baseUri = "https://api.github.com",
        defaultHeaders = {
            this
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer $token")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .header("Content-type", "application/json")
        },
        requestProcessor = { Mono.just(it) }
    )
}

