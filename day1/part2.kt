import java.io.File

public class Part2Solution
  
fun main(args: Array<String>) {
    val input = readFile("input.txt")
    var curSum: Int = 0
    val maxes = intArrayOf(0,0,0) // An array to hold the top sums. Highest number first
    for (line in input) {
        if (line == "") { // end of current elf's inventory
            updateMaxes(maxes, curSum)
            curSum = 0
        } else {
            val cur = line.toInt()
            curSum += cur
        }
    }
    // Account for last elf
    updateMaxes(maxes, curSum)
    val answer = maxes.sum()
    println(answer)
}

fun updateMaxes(arr: IntArray, cur: Int) {
    if (cur > arr[0]) { // New highest
        val temp = arr[0]
        arr[0] = cur
        updateMaxes(arr, temp)
        return
    } else if (cur > arr[1]) {
        val temp = arr[1]
        arr[1] = cur
        updateMaxes(arr, temp)
        return
    } else if (cur > arr[2]) {
        arr[2] = cur
        return
    }
}

fun readFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
