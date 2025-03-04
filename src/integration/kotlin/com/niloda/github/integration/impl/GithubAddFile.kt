@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration.impl

import arrow.core.raise.Raise
import com.niloda.github.spi.domain.AddFile

class GithubAddFile(
    private val org: String,
    private val githubApiCaller: GithubApiCaller
) : AddFile {
    context(Raise<Throwable>)
    override suspend fun invoke(repo: String, filename: String, commitMessage: String) {
        githubApiCaller.addFile(org, repo, filename, commitMessage)
    }
}