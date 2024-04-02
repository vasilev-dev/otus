package dev.vasilev.fastify.logging.common

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class FastifyLoggerProvider(
    private val provider: (String) -> IFastifyLogWrapper = { IFastifyLogWrapper.DEFAULT }
) {
    fun logger(loggerId: String) = provider(loggerId)
    fun logger(clazz: KClass<*>) = provider(clazz.qualifiedName ?: clazz.simpleName ?: "(unknown)")

    fun logger(function: KFunction<*>) = provider(function.name)
}