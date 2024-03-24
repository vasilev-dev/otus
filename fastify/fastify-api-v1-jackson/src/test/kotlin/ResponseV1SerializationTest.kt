import dev.vasilev.fastify.api.v1.models.SchemeResponse
import dev.vasilev.fastify.api.v1.models.FastingResponse
import dev.vasilev.fastify.fastify.api.v1.apiV1Mapper
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1SerializationTest {
    private val response = FastingResponse(
        id = 1,
        startDate = "2024-03-18T13:09:21.790Z",
        expectedEndDate = "2024-03-19T13:09:21.790Z",
        actualEndDate = null,
        canceled = false,
        scheme = SchemeResponse(
            id = 1,
            name = "16-8",
            difficulty = 1,
            fastingPeriod = 16,
            mealPeriod = 8
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"id\":\\s*1"))
        assertContains(json, Regex("\"actualEndDate\":\\s*null"))
        assertContains(json, Regex("\"canceled\":\\s*false"))
        assertContains(json, Regex("\"name\":\\s*\"16-8\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, FastingResponse::class.java)

        assertEquals(response, obj)
    }
}