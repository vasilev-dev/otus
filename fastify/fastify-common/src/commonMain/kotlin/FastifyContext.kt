import kotlinx.datetime.Instant
import models.CorrelationId
import models.State
import models.debug.Debug
import models.fasting.CancelCurrentFastingCommand
import models.fasting.Fasting
import models.scheme.Scheme
import models.scheme.SetDefaultSchemeCommand
import models.Error

data class FastifyContext(
    var debug: Debug = Debug.NONE,
    var correlationId: CorrelationId = CorrelationId.NONE,
    var timeStart: Instant = Instant.NONE,

    var state: State = State.NONE,
    var errors: MutableList<Error> = mutableListOf(),

    // fasting
    var cancelCurrentFastingCommand: CancelCurrentFastingCommand = CancelCurrentFastingCommand(),
    var fastingResult: Fasting = Fasting(),
    var manyFastingsResult: MutableList<Fasting> = mutableListOf(),

    // scheme
    var setDefaultSchemeCommand: SetDefaultSchemeCommand = SetDefaultSchemeCommand(),
    var schemeResult: Scheme = Scheme(),
    var manySchemesResult: MutableList<Scheme> = mutableListOf()
)