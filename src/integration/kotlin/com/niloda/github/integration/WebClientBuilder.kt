package com.niloda.github.integration

import arrow.core.raise.Raise
import arrow.core.raise.catch
import arrow.integrations.jackson.module.registerArrowModule
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.http.codec.ServerSentEventHttpMessageReader
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Mono

class WebClientBuilder(
    val baseUri: String,
    val defaultHeaders: WebClient.RequestHeadersSpec<*>.() -> WebClient.RequestHeadersSpec<*> = { this },
    val requestProcessor: ((ClientRequest) -> Mono<ClientRequest>)? = null
) {

    fun buildClient() =
        WebClient.builder()
            .codecs { registerArrowModule(it) }
            .baseUrl(baseUri)
            .applyRequestProcessorIf()
            .build()

    private fun registerArrowModule(it: ClientCodecConfigurer) {
        val reader = it
            .readers
            .filterIsInstance<ServerSentEventHttpMessageReader>()
            .single()
            .decoder as Jackson2JsonDecoder
        reader.objectMapper.registerArrowModule()
    }

    fun WebClient.Builder.applyRequestProcessorIf() =
        also {
            if (requestProcessor != null)
                it.filter(ExchangeFilterFunction.ofRequestProcessor(requestProcessor))
        }

    context(Raise<Throwable>)
    suspend inline fun <reified T : Any> get(uri: String): T =
        catch({
            getter()
                .uri(uri)
                .defaultHeaders()
                .retrieve()
                .awaitBody()
        }) {
            raise(it)
        }


    context(Raise<Throwable>)
    suspend inline fun <reified T : Any, B : Any> post(uri: String, body: B) =
        catch({
            poster()
                .uri(uri)
                .bodyValue(body)
                .defaultHeaders()
                .retrieve()
                .awaitBody<T>()
        }) {
            raise(it)
        }

    context(Raise<Throwable>)
    suspend inline fun <reified T : Any, B : Any> put(uri: String, body: B) =
        catch({
            putter()
                .uri(uri)
                .bodyValue(body)
                .defaultHeaders()
                .retrieve()
                .awaitBody<T>()
        }) {
            raise(it)
        }
    context(Raise<Throwable>)
    suspend inline fun <reified T : Any> put(uri: String) =
        catch({
            putter()
                .uri(uri)
                .defaultHeaders()
                .retrieve()
                .awaitBody<T>()
        }) {
            raise(it)
        }



    context(Raise<Throwable>)
    suspend inline fun <reified T : Any> delete(uri: String) =
        catch({
            deleter()
                .uri(uri)
                .defaultHeaders()
                .retrieve()
                .awaitBody<T>()
        }) {
            raise(it)
        }

    context(Raise<Throwable>)
    suspend inline fun <reified T : Any, B : Any> patch(uri: String, body: B) =
        catch({
            patcher()
                .uri(uri)
                .bodyValue(body)
                .defaultHeaders()
                .retrieve()
                .awaitBody<T>()
        }) {
            raise(it)
        }

    fun getter() = buildClient().get()

    fun poster() = buildClient().post()

    fun putter() = buildClient().put()

    fun patcher() = buildClient().patch()

    private fun addRequestLogger(requestProcessor: (ClientRequest) -> Mono<ClientRequest>):
                (MutableList<ExchangeFilterFunction>) -> Unit = { exchange ->
        exchange.add(ExchangeFilterFunction.ofRequestProcessor { request -> requestProcessor(request) })
    }

    fun deleter() = buildClient().delete()
}