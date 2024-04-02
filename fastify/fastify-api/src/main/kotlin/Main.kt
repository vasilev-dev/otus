package dev.vasilev.fastify.fastify.api

import fastifyLoggerLogback
import kotlinx.coroutines.delay

suspend fun main() {
    val logger = fastifyLoggerLogback("fastify-api")
    while (true) {
        logger.info(msg = "Hello, World")
        delay(500)
    }
}