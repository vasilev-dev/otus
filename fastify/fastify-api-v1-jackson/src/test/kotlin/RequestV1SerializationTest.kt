import dev.vasilev.fastify.api.apiV1Mapper
import dev.vasilev.fastify.api.v1.models.CancelCurrentFastingRequest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1SerializationTest {
    private val request = CancelCurrentFastingRequest(save = true)

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"save\":\\s*true"))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, CancelCurrentFastingRequest::class.java)

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"save": true}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, CancelCurrentFastingRequest::class.java)

        assertEquals(request, obj)
    }
}