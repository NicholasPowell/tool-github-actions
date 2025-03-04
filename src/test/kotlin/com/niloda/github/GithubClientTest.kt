package com.niloda.github

import com.niloda.github.integration.GithubClient
import com.niloda.github.integration.GithubConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [GithubClient::class, GithubConfig::class]
)
class GithubClientTest(
    @Autowired private val githubClient: GithubClient
) {



}