package com.niloda.github.integration.domain

import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    val id: Int,
    val login: String,
    val avatarUrl: String? = null,
    val email: String? = null,
    val name: String? = null,
    val nodeId: String? = null,
    val siteAdmin: Boolean? = null,
    val url: String? = null
)


