import java.io.File

public class Part1Solution
  
fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readAndParseFile(fileName)[0]
    val map: MutableMap<Char, Int> = mutableMapOf() // Holds the seen chars and their indices
    for (idx in 0 until input.length) {
        val ch = input[idx]
        if (map.contains(ch)) {
            val prevIdxOfCh = map.get(ch)!!
            purgeMapToIndex(map, prevIdxOfCh)
        }
        map.put(ch, idx)
        if (map.size == 4) {
            println("Answer: " + (idx + 1)) // Translate to base 1
            break
        }
    }
}

fun purgeMapToIndex(map: MutableMap<Char, Int>, idx: Int) {
    val toRemove: MutableList<Char> = mutableListOf()
    for (pair in map) {
        if (pair.value <= idx) {
            toRemove.add(pair.key)
        }
    }
    
    for (ch in toRemove) {
        map.remove(ch)
    }
}

fun readAndParseFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
