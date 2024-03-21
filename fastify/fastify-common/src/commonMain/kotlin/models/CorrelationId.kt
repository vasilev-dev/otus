package models

data class CorrelationId(private val value: String) {
    override fun toString(): String = value

    companion object {
        val NONE = CorrelationId("")
    }
}