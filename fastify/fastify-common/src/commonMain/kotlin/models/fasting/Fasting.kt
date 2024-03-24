package models.fasting

import NONE
import kotlinx.datetime.Instant
import models.scheme.Scheme

data class Fasting(
    val id: FastingId = FastingId.NONE,
    val startDate: Instant = Instant.NONE,
    val expectedEndDate: Instant = Instant.NONE,
    val actualEndDate: Instant = Instant.NONE,
    val canceled: Boolean = false,
    val scheme: Scheme = Scheme.NONE
)
