package model

data class Course(
    val codeOperateur: String?,
    val date: String?,
    val numero: String?,
    val segment: List<Segment>,
    val desserte: List<Desserte>,
    val typeMode: String?
)