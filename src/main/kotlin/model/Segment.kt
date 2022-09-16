package model

data class Segment(
    val codePointArretDestination: String?,
    val codePointArretOrigine: String?,
    val rame: List<Rame>,
    val typeCodeDestination: String?,
    val typeCodeOrigine: String?
)