package com.niloda.github.integration.impl

import com.niloda.github.spi.domain.AddFile
import com.niloda.github.spi.domain.CreateFromTemplate
import com.niloda.github.spi.domain.CreateRepository
import com.niloda.github.spi.domain.DeleteRepository
import com.niloda.github.spi.domain.FetchRepositoryNames
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GithubAdapter(
    private val githubApiCaller: GithubApiCaller,
    private val json: Json,
    @Value("\${github.org:niloda-me}")
    private val org: String
) {


    @Bean
    fun fetch(): FetchRepositoryNames = GithubFetchRepositoryNames(
        json = json,
        githubApiCaller = githubApiCaller,
        org = org
    )

    @Bean
    fun create(): CreateRepository = GithubCreateRepository(
        githubApiCaller = githubApiCaller,
        org = org
    )

    @Bean
    fun delete(): DeleteRepository = GithubDeleteRepository(
        githubApiCaller = githubApiCaller,
        org = org
    )

    @Bean
    fun addFile(): AddFile = GithubAddFile(
        githubApiCaller = githubApiCaller,
        org = org
    )

    @Bean
    fun createFromTemplate(): CreateFromTemplate = GithubCreateFromTemplate(
        githubApiCaller = githubApiCaller,
        org = org
    )
}

