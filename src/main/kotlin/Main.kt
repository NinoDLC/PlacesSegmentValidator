import com.google.gson.Gson
import model.CourseResponse
import java.io.File

fun main(args: Array<String>) {
    println("Hello Wafae!")
    println("Mode [1] Segment ou Mode [2] Desserte ?")
    var mode = readLine()

    while (!mode.isNullOrBlank()) {
        when (mode) {
            "1" -> {
                println("Segment mode activated!")
                SegmentMode()
            }
            "2" -> {
                println("Desserte mode activated!")
                DesserteMode()
            }
            else -> println("Choisi un mode avec 1 ou 2 !")
        }

        println("Mode [1] Segment ou Mode [2] Desserte ?")
        mode = readLine()
    }
}