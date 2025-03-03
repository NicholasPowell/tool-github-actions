package com.niloda.github.integration

import arrow.core.raise.Raise
import com.niloda.github.integration.actions.CreateOrgRepoFullRequest
import com.niloda.github.integration.actions.GenerateFromTemplateRequest
import com.niloda.github.integration.domain.GithubRepository
import com.niloda.github.spi.domain.AddFile
import com.niloda.github.spi.domain.CreateFromTemplate
import com.niloda.github.spi.domain.CreateRepository
import com.niloda.github.spi.domain.DeleteRepository
import com.niloda.github.spi.domain.FetchRepositoryNames
import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GithubAdapter(
    private val githubApiCaller: GithubApiCaller,
    private val json: Json
) {
    val org = "niloda-me"

    @Bean
    fun fetch(): FetchRepositoryNames = object : FetchRepositoryNames {
        context(Raise<Throwable>)
        override suspend fun invoke(): List<String> =
            json
                .decodeFromString<List<GithubRepository>>(
                    githubApiCaller.listReposForOrg(org)
                ).map { it.name }
    }

    @Bean
    fun create(): CreateRepository = object : CreateRepository {
        context(Raise<Throwable>)
        override suspend fun invoke(
            name: String,
            isTemplate: Boolean
        ) {
            githubApiCaller.createOrgRepo(
                org = org,
                request = CreateOrgRepoFullRequest(
                    name = name,
                    isTemplate = isTemplate
                )
            )
        }
    }

    @Bean
    fun createFromTemplate(): CreateFromTemplate = object : CreateFromTemplate {

        context(Raise<Throwable>)
        override suspend fun invoke(template: String, name: String) {
            githubApiCaller.createRepoFromTemplate(
                org = org,
                template = template,
                request = GenerateFromTemplateRequest(
                    name = name,
                    owner = org,
                    includeAllBranches = false,
                    private = true,
                    description = "description"
                )
            )
        }
    }

    @Bean
    fun delete(): DeleteRepository = object : DeleteRepository {

        context(Raise<Throwable>)
        override suspend fun invoke(name: String) {
            githubApiCaller.deleteOrgRepo(org = org, repo = name)
        }
    }

    @Bean
    fun addFile(): AddFile = object: AddFile {
        context(Raise<Throwable>)
        override suspend fun invoke(org: String, repo: String, filename: String) {
            githubApiCaller.addFile(org, repo, filename)
        }
    }
}

data class Repo(val name: String)
//data class Inner(val repos: List<Repo>)
//data class Result(val inner: List<Inner>)

