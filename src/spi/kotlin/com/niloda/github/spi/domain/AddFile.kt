@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.spi.domain

import arrow.core.raise.Raise

interface AddFile {
    context(Raise<Throwable>)
    suspend operator fun invoke(repo: String, filename: String, commitMessage: String)
}