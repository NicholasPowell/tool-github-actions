package com.niloda.github.integration.domain

import kotlinx.serialization.Serializable

@Serializable
data class CreateRepo(
    val name: String,
    val description: String? = null,
) {
    fun asFullRequest(): CreateOrgRepoFullRequest =
        CreateOrgRepoFullRequest(
            name = name,
            description = description,
            private = true
        )
}