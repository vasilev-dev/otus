package models.scheme

data class SchemeId(private val value: Int) {
    fun toInt(): Int = value

    companion object {
        val NONE = SchemeId(0)
    }
}
