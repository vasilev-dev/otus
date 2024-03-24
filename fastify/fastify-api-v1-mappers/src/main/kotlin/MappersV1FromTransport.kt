import dev.vasilev.fastify.api.v1.models.*
import models.debug.Environment
import models.debug.Stub
import models.fasting.CancelCurrentFastingCommand
import models.scheme.SchemeId
import models.scheme.SetDefaultSchemeCommand

fun FastifyContext.fromTransport(request: CancelCurrentFastingRequest, environment: EnvironmentParam?, stub: StubParam?) {
    cancelCurrentFastingCommand = request.save?.let { CancelCurrentFastingCommand(it) } ?: CancelCurrentFastingCommand()
    debug.environment = environment.fromTransport()
    debug.stub = stub.fromTransport()
}

fun FastifyContext.fromTransport(setDefaultSchemeId: Int?, environment: EnvironmentParam?, stub: StubParam?) {
    setDefaultSchemeCommand = setDefaultSchemeId?.let { SetDefaultSchemeCommand(SchemeId(it)) } ?: SetDefaultSchemeCommand()
    debug.environment = environment.fromTransport()
    debug.stub = stub.fromTransport()
}

private fun EnvironmentParam?.fromTransport(): Environment = when(this) {
    EnvironmentParam.PROD -> Environment.PROD
    EnvironmentParam.STUB -> Environment.STUB
    EnvironmentParam.TEST -> Environment.TEST
    null -> Environment.PROD
}

private fun StubParam?.fromTransport(): Stub = when(this) {
    StubParam.SUCCESS -> Stub.SUCCESS
    StubParam.NO_CONTENT -> Stub.NO_CONTENT
    StubParam.CLIENT_ERROR -> Stub.CLIENT_ERROR
    StubParam.SERVER_ERROR -> Stub.SERVER_ERROR
    StubParam.NOT_FOUND_ERROR -> Stub.NOT_FOUND_ERROR
    StubParam.UNAUTHORIZED_ERROR -> Stub.UNAUTHORIZED_ERROR
    null -> Stub.SUCCESS
}