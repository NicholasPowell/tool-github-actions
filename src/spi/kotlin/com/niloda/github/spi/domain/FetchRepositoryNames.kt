package com.niloda.github.spi.domain

import arrow.core.raise.Raise

interface FetchRepositoryNames {
    context(Raise<Throwable>)
    suspend operator fun invoke(): List<String>
}

interface CreateRepository {
    context(Raise<Throwable>)
    suspend operator fun invoke(name: String, isTemplate: Boolean = false)
}

interface CreateFromTemplate {
    context(Raise<Throwable>)
    suspend operator fun invoke(template: String, name: String)
}

interface DeleteRepository {
    context(Raise<Throwable>)
    suspend operator fun invoke(name: String)
}

interface AddFile {
    context(Raise<Throwable>)
    suspend operator fun invoke(org: String, repo: String, filename: String)
}
