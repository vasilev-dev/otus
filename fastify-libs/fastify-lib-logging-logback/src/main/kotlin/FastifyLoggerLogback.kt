import ch.qos.logback.classic.Logger
import dev.vasilev.fastify.logging.common.IFastifyLogWrapper
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

/**
 * Generate internal FastifyLogContext logger
 *
 * @param logger Logback instance from [LoggerFactory.getLogger()]
 */
fun fastifyLoggerLogback(logger: Logger): IFastifyLogWrapper = FastifyLogWrapperLogback(
    logger = logger,
    loggerId = logger.name,
)

fun fastifyLoggerLogback(clazz: KClass<*>): IFastifyLogWrapper = fastifyLoggerLogback(LoggerFactory.getLogger(clazz.java) as Logger)
@Suppress("unused")
fun fastifyLoggerLogback(loggerId: String): IFastifyLogWrapper = fastifyLoggerLogback(LoggerFactory.getLogger(loggerId) as Logger)