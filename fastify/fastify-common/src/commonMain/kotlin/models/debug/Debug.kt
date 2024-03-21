package models.debug

data class Debug(
    var environment: Environment = Environment.PROD,
    var stub: Stub = Stub.SUCCESS
) {
    companion object {
        val NONE = Debug()
    }
}