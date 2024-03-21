package models.scheme

data class Scheme(
    val id: SchemeId = SchemeId.NONE,
    val name: String = "",
    val description: String = "",
    val difficulty: Int = 0,
    val fastingPeriod: Int = 0,
    val mealPeriod: Int = 0
) {
    companion object {
        val NONE = Scheme()
    }
}