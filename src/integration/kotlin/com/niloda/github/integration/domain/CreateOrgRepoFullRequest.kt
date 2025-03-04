package com.niloda.github.integration.domain

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrgRepoFullRequest(
    val name: String,
    val description: String? = null,
    val homepage: String? = null,
    val private: Boolean? = null,
    val visibility: String? = null,
    val hasIssues: Boolean? = null,
    val hasProjects: Boolean? = null,
    val hasWiki: Boolean? = null,
    val hasDownloads: Boolean? = null,
    val isTemplate: Boolean? = null,
    val teamId: Int? = null,
    val autoInit: Boolean? = null,
    val gitIgnoreTemplate: String? = null,
    val licenseTemplate: String? = null,
    val allowSquashMerge: Boolean? = null,
    val allowMergeCommit: Boolean? = null,
    val allowRebaseMerge: Boolean? = null,
    val allowAutoMerge: Boolean? = null,
    val deleteBranchOnMerge: Boolean? = null,
    val useSquashPrTitleAsDefault: Boolean? = null,
    val squashMergeCommitTitle: String? = null,
    val squashMergeCommitMessage: String? = null,
    val mergeCommitTitle: String? = null,
    val mergeCommitMessage: String? = null
)
