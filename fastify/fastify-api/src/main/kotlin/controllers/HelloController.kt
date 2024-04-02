package dev.vasilev.fastify.fastify.api.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/hello")
class HelloController {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    suspend fun index(): String {
        logger.trace("Trace")
        logger.debug("Debug")
        logger.info("Info")
        logger.warn("Warn")
        logger.error("Error")

        return "Hello, world"
    }
}