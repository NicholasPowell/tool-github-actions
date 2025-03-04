//package com.niloda.github
//
//import arrow.core.raise.recover
//import com.niloda.github.spi.domain.AddFile
//import com.niloda.github.spi.domain.CreateFromTemplate
//import com.niloda.github.spi.domain.CreateRepository
//import com.niloda.github.spi.domain.DeleteRepository
//import com.niloda.github.spi.domain.FetchRepositoryNames
//import kotlinx.coroutines.runBlocking
//import org.springframework.beans.factory.getBean
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.runApplication
//
//@SpringBootApplication
//class GithubApp
//
//fun main(args: Array<String>) {
//    val app = runApplication<GithubApp>(*args)
//    val fetchRepositoryNames = app.getBean<FetchRepositoryNames>()
//    val createRepository = app.getBean<CreateRepository>()
//    val createFromTemplate = app.getBean<CreateFromTemplate>()
//    val deleteRepository = app.getBean<DeleteRepository>()
//    val addFile = app.getBean<AddFile>()
//    runBlocking {
//        recover(
//            {
//                addFile(
//                    "niloda-me",
//                    "template2",
//                    "/Users/nikpowell/home/tool-github-actions/README.md"
//                ).let { println(it) }
////                createFromTemplate("template2", "copied")
////                println(fetchRepositoryNames())
//            }
//        ) {
//            println(it)
//        }
//    }
//}