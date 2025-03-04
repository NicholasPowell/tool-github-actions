@file:Suppress("CONTEXT_RECEIVERS_DEPRECATED")

package com.niloda.github.integration

import arrow.core.raise.Raise
import arrow.core.raise.catch
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText


class HttpClientBuilder(
    val baseUri: String,
    val defaultHeaders: HttpRequestBuilder.() -> Unit = { this }
) {

    context(Raise<Throwable>)
    suspend inline fun get(uri: String): String =
        catch({
            val client = HttpClient(CIO)
            val response = client.get(baseUri + uri, defaultHeaders)
            return response.bodyAsText()

        }) {
            raise(it)
        }

    context(Raise<Throwable>)
    suspend inline fun <reified B : Any, reified R: Any> post(uri: String, body: B) =
        catch({
            HttpClient(CIO).post(baseUri + uri) { setBody<B>(body); defaultHeaders() }.body<R>() }
        ) { raise(it) }

    context(Raise<Throwable>)
        suspend inline fun <reified T : Any, reified B : Any> put(uri: String, body: B) =
            catch({
                HttpClient(CIO).put(baseUri + uri) { setBody<B>(body); defaultHeaders() }.body<T>()
            }) { raise(it) }


    context(Raise<Throwable>)
        suspend inline fun <reified T : Any> delete(uri: String) =
            catch({
                HttpClient(CIO).delete(baseUri + uri) { defaultHeaders() }.body<T>()
            }) { raise(it) }

    context(Raise<Throwable>)
        suspend inline fun <reified T : Any, reified B : Any> patch(uri: String, body: B) =
            catch({
                HttpClient(CIO).patch(baseUri + uri) { setBody<B>(body); defaultHeaders() }.body<T>()
            }) { raise(it) }

}