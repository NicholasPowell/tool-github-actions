package com.niloda.github.integration.api

import arrow.core.raise.Raise
import com.niloda.github.integration.domain.GenerateFromTemplateRequest
import kotlinx.serialization.encodeToString

interface CreateRepoFromTemplate: GithubCaller {

    context(Raise<Throwable>)
    @Suppress("CONTEXT_RECEIVERS_DEPRECATED")
    suspend fun createRepoFromTemplate(
        org: String,
        template: String,
        request: GenerateFromTemplateRequest
    ): String =
        httpClient.post(
            "repos/$org/$template/generate".also{ println(it) },
            json.encodeToString(request).also { println(it) }
        ) ?: "NULL"
}