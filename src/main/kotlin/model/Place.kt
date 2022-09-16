package model

data class Place(
    val attributsCaracteristiqueOccupation: AttributsCaracteristiqueOccupation?,
    val attributsCaracteristiquePlace: List<AttributsCaracteristiquePlace>,
    val classe: String?,
    val numero: String?
)