@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.impl

import arrow.core.raise.Raise
import com.niloda.github.integration.domain.GenerateFromTemplateRequest
import com.niloda.github.spi.domain.CreateFromTemplate

class GithubCreateFromTemplate(
    private val githubApiCaller: GithubApiCaller,
    private val org: String
) : CreateFromTemplate {

    context(Raise<Throwable>)
    override suspend fun invoke(template: String, name: String) {
        githubApiCaller.createRepoFromTemplate(
            org = org,
            template = template,
            request = GenerateFromTemplateRequest(
                name = name,
                owner = org,
                includeAllBranches = false,
                private = true,
                description = "description"
            )
        )
    }
}