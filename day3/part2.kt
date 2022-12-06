import java.io.File

public class Part2Solution
  
fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readAndParseFile(fileName)
    var sum: Int = 0
    for (invs in input) {
        val itemMap: MutableMap<Char, Int> = mutableMapOf()
        for (inv in invs) { // Loop through each elf's inventory
            val itemSet: MutableSet<Char> = mutableSetOf() // for removing dups
            for (item in inv) { // Loop through the items
                itemSet.add(item)
            }
            for (uniqItem in itemSet) {
                addItemToMap(itemMap, uniqItem)
            }
        }
        for (itemSums in itemMap) {
            if (itemSums.value == 3) { // Shared inventory
                sum += scoreItem(itemSums.key)
                break
            }
        }
    }
    println(sum)
}

fun addItemToMap(map: MutableMap<Char, Int>, item: Char) {
    if (map.contains(item)) {
        var number = map.get(item)!! // assert non-null since we checked if it's contained
        map.put(item, ++number)
    } else {
        map.put(item, 1)
    }
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
    for (i in lines.indices step 3) {
        // Group by elf-group
        retList.add(arrayOf(lines[i], lines[i+1], lines[i+2]))
    }
    return retList
}
