package com.niloda.github.integration.domain

import kotlinx.serialization.Serializable

@Serializable
data class GithubRepository(
    val description: String?,
    val fullName: String,
    val htmlUrl: String,
    val id: Int,
    val name: String,
    val nodeId: String,
    val owner: Owner,
    val private: Boolean,
    val url: String
)