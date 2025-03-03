package com.niloda.github.integration

import arrow.core.raise.Raise
import com.niloda.github.integration.actions.GenerateFromTemplateRequest
import kotlinx.serialization.encodeToString

interface CreateRepoFromTemplate: GithubCaller {

    context(Raise<Throwable>)
    suspend fun createRepoFromTemplate(
        org: String,
        template: String,
        request: GenerateFromTemplateRequest
    ): String =
        webClient.post(
            "repos/$org/$template/generate".also{ println(it) },
            json.encodeToString(request).also { println(it) }
        ) ?: "NULL"
}