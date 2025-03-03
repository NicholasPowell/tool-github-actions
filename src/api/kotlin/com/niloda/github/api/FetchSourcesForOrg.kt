package com.niloda.github.api

interface FetchSourcesForOrg {
    operator fun invoke(): List<String>
}