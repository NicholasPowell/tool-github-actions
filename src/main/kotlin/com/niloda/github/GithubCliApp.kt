package com.niloda.github

import arrow.core.raise.recover
import com.niloda.github.spi.domain.AddFile
import com.niloda.github.spi.domain.CreateFromTemplate
import com.niloda.github.spi.domain.CreateRepository
import com.niloda.github.spi.domain.DeleteRepository
import com.niloda.github.spi.domain.FetchRepositoryNames
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GithubCliApp {

    @Bean
    fun commandLineRunner(
        fetchRepositoryNames: FetchRepositoryNames,
        createRepository: CreateRepository,
        createFromTemplate: CreateFromTemplate,
        deleteRepository: DeleteRepository,
        addFile: AddFile
    ) = CommandLineRunner { args ->
        runBlocking {
            recover({
                when (args.getOrNull(0)) {
                    "fetch" -> println(fetchRepositoryNames())
                    "create" -> {
                        val name = args.getOrNull(1) ?: error("Repository name required")
                        val isTemplate = args.getOrNull(2)?.toBoolean() == true
                        createRepository(name, isTemplate)
                        println("Repository $name created")
                    }
                    "template" -> {
                        val template = args.getOrNull(1) ?: error("Template name required")
                        val name = args.getOrNull(2) ?: error("New repository name required")
                        createFromTemplate(template, name)
                        println("Repository $name created from template $template")
                    }
                    "delete" -> {
                        val name = args.getOrNull(1) ?: error("Repository name required")
                        deleteRepository(name)
                        println("Repository $name deleted")
                    }
                    "add-file" -> {
                        val repo = args.getOrNull(1) ?: error("Repository required")
                        val filename = args.getOrNull(2) ?: error("Filename required")
                        val commitMessage = args.getOrNull(3) ?: error("Commit message required")
                        addFile(repo, filename, commitMessage)
                        println("File $filename added to $repo")
                    }
                    else -> println("""
                        Usage:
                        fetch - List repositories
                        create <name> [isTemplate] - Create repository
                        template <template> <name> - Create from template
                        delete <name> - Delete repository
                        add-file <org> <repo> <filename> <commitMessage> - Add file to repository
                    """.trimIndent())
                }
            }) {
                println("Error: ${it.message}")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<GithubCliApp>(*args)
}