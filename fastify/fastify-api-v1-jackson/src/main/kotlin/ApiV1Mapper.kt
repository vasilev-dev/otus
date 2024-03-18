package dev.vasilev.fastify.api

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

val apiV1Mapper: ObjectMapper = JsonMapper.builder()
    .enable(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL)
    .build()
    .registerKotlinModule()
    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)