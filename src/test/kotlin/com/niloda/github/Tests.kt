//package com.niloda.github.tool
//
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockserver.integration.ClientAndServer
//import org.mockserver.junit.jupiter.MockServerExtension
//import org.mockserver.mock.Expectation
//import org.mockserver.model.HttpRequest.request
//import org.mockserver.model.HttpResponse.response
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import java.net.URI
//import java.net.http.HttpClient
//import java.net.http.HttpRequest
//import java.net.http.HttpResponse
//
//
////@SpringBootTest(classes = [GithubApp::class])
//@ExtendWith(MockServerExtension::class)
//class Tests(private val mockServerClient: ClientAndServer) {
//
//    @Test
//    fun testGoooo() {
//        mockServerClient.`when`(request()).respond(response().withBody("NICE"))
//        val request = HttpRequest.newBuilder()
//            .uri(URI.create("http://localhost:${mockServerClient.port}/test"))
//            .build()
//        val response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString())
//        println(response.body())
//    }
//    @Test
//    fun tesyt2() {
//        println("goooo")
//    }
//}