import com.google.gson.Gson
import model.CourseResponse
import java.io.File

private const val OCCUPATION_LIBRE = "LIBRE"
private const val OCCUPATION_OCCUPIED = "OCCUPE"
private const val OCCUPATION_BLOCKED = "BLOQUE"

class DesserteMode {
    init {
        println("Quel est le numéro de voiture ?")
        var carNumber = readLine()
        println("Quel est le numéro de place ? (entrée pour ne rien donner)")
        var seatNumber = readLine().takeIf { !it.isNullOrBlank() }

        while (!carNumber.isNullOrBlank()) {

            println()
            println("Tu as choisi la voiture: $carNumber")
            if (seatNumber == null) {
                println("Tu n'as pas choisi de numéro de place, toutes les places vont être affichées")
            } else {
                println("Tu as choisi la place: $seatNumber")
            }

            val courseResponse = Gson().fromJson(
                File("data/occupationDesPlaces.json").bufferedReader(),
                CourseResponse::class.java
            )

            if (courseResponse != null) {
                if (courseResponse.course != null) {
                    courseResponse.course.desserte.forEach { desserte ->
                        println()
                        println("${desserte.codePointArret}")
                        val rame = desserte.rame.firstOrNull()
                        if (rame != null) {
                            val car = rame.voiture.find { it.numero == carNumber }
                            if (car != null) {
                                if (seatNumber == null) {
                                    val availableSeats = car.place.filter {
                                        it.attributsCaracteristiqueOccupation?.statutDistribution == OCCUPATION_LIBRE
                                    }.map {
                                        it.numero
                                    }
                                    if (availableSeats.isNotEmpty()) {
                                        println("Available seats")
                                        println(availableSeats)
                                    }

                                    val occupiedSeats = car.place.filter {
                                        it.attributsCaracteristiqueOccupation?.statutDistribution == OCCUPATION_OCCUPIED
                                    }.map {
                                        it.numero
                                    }
                                    if (occupiedSeats.isNotEmpty()) {
                                        println("Occupied seats")
                                        println(occupiedSeats)
                                    }

                                    val blockedSeats = car.place.filter {
                                        it.attributsCaracteristiqueOccupation?.statutDistribution == OCCUPATION_BLOCKED
                                    }.map {
                                        it.numero
                                    }
                                    if (blockedSeats.isNotEmpty()) {
                                        println("Blocked seats")
                                        println(blockedSeats)
                                    }

                                    val wtfSeats = car.place.filter {
                                        it.attributsCaracteristiqueOccupation?.statutDistribution == null ||
                                            it.attributsCaracteristiqueOccupation.statutDistribution == "null" ||
                                            it.attributsCaracteristiqueOccupation.statutDistribution.isBlank()
                                    }.map {
                                        "La place ${it.numero} a comme attributsCaracteristiqueOccupation.statutDistribution = ${it.attributsCaracteristiqueOccupation?.statutDistribution}"
                                    }
                                    if (wtfSeats.isNotEmpty()) {
                                        println("WTF seats")
                                        println(wtfSeats)
                                    }
                                } else {
                                    val seat = car.place.find { it.numero == seatNumber }

                                    if (seat != null) {
                                        println("La place $seatNumber a comme statutDistribution : ${seat.attributsCaracteristiqueOccupation?.statutDistribution}")
                                    } else {
                                        println("!!! Je n'ai pas trouvé la place ayant comme numéro : $seatNumber dans la voiture $carNumber")
                                    }
                                }
                            } else {
                                println("Parsing error : car is NULL !")
                            }
                        } else {
                            println("Parsing error : rame is NULL !")
                        }
                    }
                } else {
                    println("Parsing error : course is NULL !")
                }
            } else {
                println("Parsing error : courseResponse is NULL !")
            }

            println()
            println()
            println("Quel est le numéro de voiture ?")
            carNumber = readLine()
            println("Quel est le numéro de place ? (entrée pour ne rien donner)")
            seatNumber = readLine()
        }
    }
}
