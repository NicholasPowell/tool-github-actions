@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.spi.domain

import arrow.core.raise.Raise

interface CreateFromTemplate {
    context(Raise<Throwable>)
    suspend operator fun invoke(template: String, name: String)
}