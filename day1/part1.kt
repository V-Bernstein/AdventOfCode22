import java.io.File

public class Part1Solution
  
fun main(args: Array<String>) {
    val input = readFile("input.txt")
    var max: Int = 0
    var curSum: Int = 0
    for (line in input) {
        if (line == "") { // end of current elf's inventory
            if (curSum > max) {
                max = curSum
            }
            curSum = 0
            continue
        }
        val cur = line.toInt()
        curSum += cur
    }
    // Account for last elf
    if (curSum > max) {
        max = curSum
    }
    println(max)
}

fun readFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
