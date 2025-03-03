package com.niloda.github.integration.actions

import kotlinx.serialization.Serializable

@Serializable
data class GenerateFromTemplateRequest(
    val owner: String,
    val name: String,
    val description: String?,
    val includeAllBranches: Boolean,
    val private: Boolean
)
