@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.spi.domain

import arrow.core.raise.Raise

interface CreateRepository {
    context(Raise<Throwable>)
    suspend operator fun invoke(name: String, isTemplate: Boolean = false)
}