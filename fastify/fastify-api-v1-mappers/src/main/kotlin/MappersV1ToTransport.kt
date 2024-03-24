import dev.vasilev.fastify.api.v1.models.ErrorResponse
import dev.vasilev.fastify.api.v1.models.FastingResponse
import dev.vasilev.fastify.api.v1.models.SchemeResponse
import kotlinx.datetime.Instant
import models.Error
import models.scheme.Scheme

fun FastifyContext.toFastingResponse(): FastingResponse {
    return FastingResponse(
        id = fastingResult.id.toInt(),
        startDate = fastingResult.startDate.toString(),
        expectedEndDate = fastingResult.expectedEndDate.toString(),
        actualEndDate = if (fastingResult.actualEndDate == Instant.NONE) null else fastingResult.actualEndDate.toString(),
        canceled = fastingResult.canceled,
        scheme = fastingResult.scheme.toSchemeResponse()
    )
}

fun FastifyContext.toManyFastingsResponse(): List<FastingResponse> {
    return manyFastingsResult.map { toFastingResponse() }
}

fun Scheme.toSchemeResponse(): SchemeResponse {
    return SchemeResponse(
        id = this.id.toInt(),
        name = this.name,
        description = this.description,
        difficulty = this.difficulty,
        fastingPeriod = this.fastingPeriod,
        mealPeriod = this.mealPeriod
    )
}

fun FastifyContext.toSchemeResponse(): SchemeResponse {
    return schemeResult.toSchemeResponse()
}

fun FastifyContext.toManySchemesResponse(): List<SchemeResponse> {
    return manySchemesResult.map { toSchemeResponse() }
}

fun Error?.toErrorResponse(): ErrorResponse {
    return this?.let {
        ErrorResponse(
            code = this.code,
            message = this.message,
            field = this.field
        )
    } ?: ErrorResponse()
}

fun FastifyContext.toErrorResponse(): ErrorResponse {
    val error = errors.getOrNull(0)
    return error.toErrorResponse()
}

fun FastifyContext.toManyErrorsResponse(): List<ErrorResponse> {
    return errors.map { toErrorResponse() }
}