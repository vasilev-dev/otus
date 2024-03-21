package models.fasting

data class FastingId(private val value: Int) {
    fun toInt(): Int = value

    companion object {
        val NONE = FastingId(0)
    }
}
