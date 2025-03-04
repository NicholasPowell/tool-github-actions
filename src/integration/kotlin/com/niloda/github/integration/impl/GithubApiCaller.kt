package com.niloda.github.integration.impl

import com.niloda.github.integration.api.AddFileCall
import com.niloda.github.integration.api.GetRepo
import com.niloda.github.integration.api.GithubCaller
import com.niloda.github.integration.GithubProperties
import com.niloda.github.integration.api.ListRepos
import com.niloda.github.integration.HttpClientBuilder
import com.niloda.github.integration.api.CreateRepo
import com.niloda.github.integration.api.CreateRepoFromTemplate
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

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

    override val httpClient: HttpClientBuilder
        get() = httpClientInit

    private val httpClientInit = HttpClientBuilder(
        baseUri = "https://api.github.com/",
        defaultHeaders = {
                headers {
                    header("Accept", "application/vnd.github+json")
                    header("Authorization", "Bearer $token")
                    header("X-GitHub-Api-Version", "2022-11-28")
                    header("Content-type", "application/json")
                }
            }
    )
}

