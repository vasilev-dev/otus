import dev.vasilev.fastify.api.v1.models.CancelCurrentFastingRequest
import dev.vasilev.fastify.api.v1.models.EnvironmentParam
import dev.vasilev.fastify.api.v1.models.StubParam
import models.debug.Environment
import models.debug.Stub
import kotlin.test.Test
import kotlin.test.assertEquals

class MappersV1FromTransportTest {
    @Test
    fun cancelCurrentFastingRequestToContext() {
        val request = CancelCurrentFastingRequest(save = true)
        val environment = EnvironmentParam.TEST
        val stub = StubParam.SUCCESS

        val context = FastifyContext()
        context.fromTransport(request, environment, stub)

        assertEquals(true, context.cancelCurrentFastingCommand.save)
        assertEquals(Environment.TEST, context.debug.environment)
        assertEquals(Stub.SUCCESS, context.debug.stub)
    }

    @Test
    fun setDefaultSchemeRequestToContext() {
        val request = 1
        val environment = EnvironmentParam.TEST
        val stub = StubParam.SUCCESS

        val context = FastifyContext()
        context.fromTransport(request, environment, stub)

        assertEquals(1, context.setDefaultSchemeCommand.id.toInt())
        assertEquals(Environment.TEST, context.debug.environment)
        assertEquals(Stub.SUCCESS, context.debug.stub)
    }
}