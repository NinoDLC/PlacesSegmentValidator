import com.google.gson.Gson
import model.CourseResponse
import java.io.File

class SegmentMode {
    init {
        println("Quel est le numéro de voiture ?")
        var carNumber = readLine()

        while (!carNumber.isNullOrBlank()) {

            println("Tu as choisi la voiture: $carNumber")

            val courseResponse = Gson().fromJson(
                File("data/placesLibresSurSegment.json").bufferedReader(),
                CourseResponse::class.java
            )

            if (courseResponse != null) {
                if (courseResponse.course != null) {
                    val segment = courseResponse.course.segment.firstOrNull()
                    if (segment != null) {
                        val rame = segment.rame.firstOrNull()
                        if (rame != null) {
                            val car = rame.voiture.find { it.numero == carNumber }
                            if (car != null) {
                                println("Available seats")
                                println("First class:")
                                println(car.place.filter { it.classe == "1" }.map { it.numero })
                                println("Second class:")
                                println(car.place.filter { it.classe == "2" }.map { it.numero })
                            } else {
                                println("Parsing error : car is NULL !")
                            }
                        } else {
                            println("Parsing error : rame is NULL !")
                        }
                    } else {
                        println("Parsing error : segment is NULL !")
                    }
                } else {
                    println("Parsing error : course is NULL !")
                }
            } else {
                println("Parsing error : courseResponse is NULL !")
            }

            println("Quel est le numéro de voiture ?")
            carNumber = readLine()
        }
    }
}
