package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.client.RxProxyHttpClient
import io.micronaut.http.filter.OncePerRequestHttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.micronaut.runtime.Micronaut.build
import org.reactivestreams.Publisher
import java.net.URI

fun main(args: Array<String>) {
    build()
            .args(*args)
            .packages("com.example")
            .start()
}

@Filter("/**")
class ProxyFilter(private val proxy: RxProxyHttpClient) : OncePerRequestHttpServerFilter() {
    override fun doFilterOnce(request: HttpRequest<*>, chain: ServerFilterChain): Publisher<MutableHttpResponse<*>> {
        val proxiedRequest = request.mutate()
                .uri(URI("http://localhost:8081"))

        println("proxy")
        return proxy.proxy(proxiedRequest)
    }

}