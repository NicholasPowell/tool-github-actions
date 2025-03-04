@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.spi.domain

import arrow.core.raise.Raise

interface DeleteRepository {
    context(Raise<Throwable>)
    suspend operator fun invoke(name: String)
}