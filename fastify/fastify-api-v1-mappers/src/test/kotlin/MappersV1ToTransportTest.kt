import kotlinx.datetime.Instant
import models.Error
import models.fasting.Fasting
import models.fasting.FastingId
import models.scheme.Scheme
import models.scheme.SchemeId
import org.junit.Test
import kotlin.test.assertEquals

class MappersV1ToTransportTest {
    @Test
    fun contextToFastingResponse() {
        val context = FastifyContext(
            fastingResult = Fasting(
                id = FastingId(1),
                startDate = Instant.parse("2020-08-17T11:20:27.207Z"),
                expectedEndDate = Instant.parse("2020-08-17T11:20:27.207Z"),
                actualEndDate = Instant.NONE,
                canceled = false,
                scheme = Scheme(
                    id = SchemeId(1),
                    name = "16-8",
                    description = "some desc",
                    difficulty = 1,
                    fastingPeriod = 16,
                    mealPeriod = 8
                )
            )
        )

        val actual = context.toFastingResponse()

        assertEquals(1, actual.id)
        assertEquals(false, actual.canceled)
        assertEquals("2020-08-17T11:20:27.207Z", actual.startDate)
        assertEquals("2020-08-17T11:20:27.207Z", actual.expectedEndDate)
        assertEquals(null, actual.actualEndDate)

        assertEquals(1, actual.scheme?.id)
        assertEquals("16-8", actual.scheme?.name)
        assertEquals(1, actual.scheme?.difficulty)
        assertEquals("some desc", actual.scheme?.description)
        assertEquals(8, actual.scheme?.mealPeriod)
        assertEquals(16, actual.scheme?.fastingPeriod)
    }

    @Test
    fun contextToErrorResponse() {
        val context = FastifyContext(
            errors = mutableListOf(
                Error(
                    code = "001",
                    field = "id",
                    message = "cannot be negative"
                )
            )
        )

        val actual = context.toErrorResponse()

        assertEquals("001", actual.code)
        assertEquals("id", actual.field)
        assertEquals("cannot be negative", actual.message)
    }
}