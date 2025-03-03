package com.niloda.github.integration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Configuration
@EnableConfigurationProperties(value = [GithubProperties::class])
class GithubConfig

@ConfigurationProperties("github")
data class GithubProperties(val token: String)

@Service
class GithubClient(
    private val githubProperties: GithubProperties
) {
    fun go() = githubProperties.token

}


