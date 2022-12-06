import java.io.File

public class Part2Solution
  
fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readAndParseFile(fileName)
    var count: Int = 0
    for (line in input) {
        val firstElf = parseSectionIntoPair(line[0])
        val secondElf = parseSectionIntoPair(line[1])
        if (overlaps(firstElf, secondElf)) {
            count ++
        }
    }
    println(count)
}

fun overlaps(first: Pair<Int, Int>, second: Pair<Int, Int>): Boolean {
    return !((first.first < second.first && first.second < second.first) || (second.first < first.first && second.second < first.first))
}

fun parseSectionIntoPair(section: String): Pair<Int, Int> {
    val sections = section.split("-")
    return Pair(sections[0].toInt(), sections[1].toInt())
}

fun readAndParseFile(fileName: String): List<List<String>> {
    val lines = File(fileName).useLines { it.toList() }
    val retList: MutableList<List<String>> = mutableListOf()
    for (line in lines) {
        // split into compartments
        retList.add(line.split(","))
    }
    return retList
}
