import java.io.File

public class Part1Solution
  
fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readAndParseFile(fileName)
    var sum: Int = 0
    for (comps in input) {
        val comp1 = comps[0]
        val comp2 = comps[1]
        val itemSet: MutableSet<Char> = mutableSetOf()
        for (item in comp1) {
            itemSet.add(item)
        }
        for (item in comp2) {
            if (itemSet.contains(item)) { // Duplicate item
                sum += scoreItem(item)
                break
            }
        }
    }
    println(sum)
}

fun scoreItem(item: Char): Int {
    if (item.code >= 'a'.code && item.code <= 'z'.code) { // item is lower case
        return item.code - 'a'.code + 1
    } else { // item is upper case
        return item.code - 'A'.code + 1 + 26
    }
}

fun readAndParseFile(fileName: String): List<Array<String>> {
    val lines = File(fileName).useLines { it.toList() }
    val retList: MutableList<Array<String>> = mutableListOf()
    for (line in lines) {
        // split into compartments
        val lineLength = line.length
        val halfPoint = lineLength / 2
        val comp1: String = line.substring(0, halfPoint)
        val comp2: String = line.substring(halfPoint, lineLength)
        retList.add(arrayOf(comp1, comp2))
    }
    return retList
}
