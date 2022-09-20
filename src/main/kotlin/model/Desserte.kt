package model

data class Desserte(
    val codePointArret: String?,
    val typeCode: String?,
    val rame: List<Rame>,
    val rangPointArret: Int?,
)