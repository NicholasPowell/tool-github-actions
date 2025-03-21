@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.spi.domain

import arrow.core.raise.Raise

interface FetchRepositoryNames {
    context(Raise<Throwable>)
    suspend operator fun invoke(): List<String>
}

