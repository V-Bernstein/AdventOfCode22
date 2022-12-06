import java.io.File
import java.util.ArrayDeque

public class Part2Solution
  
fun main(args: Array<String>) {
    val fileName = args[0]
    val inputs = readAndParseFile(fileName)
    val stacks = generateStacks(inputs[0])
    val moves = generateMoves(inputs[1])
    for (move in moves) {
        val (numMoved, from, to) = move
        val tempArr: CharArray = CharArray(numMoved)
        for (i in 0 until numMoved) {
            val crate = stacks[from - 1].removeLast()
            tempArr[i] = crate
        }
        for (i in numMoved - 1 downTo 0) {
            stacks[to-1].addLast(tempArr[i])
        }
    }
    var topCrates: CharArray = CharArray(stacks.size)
    for (i in 0 until stacks.size) {
        topCrates[i] = stacks[i].removeLast()
    }
    println(String(topCrates))
}

fun generateStacks(input: List<String>): List<ArrayDeque<Char>> {
    val numbers = input[input.size - 1].split(" ")
    val numberOfStacks = numbers[numbers.size - 2].toInt() // To account for space at end
    val retList: MutableList<ArrayDeque<Char>> = mutableListOf()
    for (i in 0 until numberOfStacks) { // Create stacks
        retList.add(ArrayDeque())
    }
    for (i in input.size - 2 downTo 0) { // Iterate from bottom of stacks to top of stacks
        var curStackIdx = 0
        for (j in 0 until input[i].length step 4) {
            val ch = input[i][j]
            if (ch == '[') { // Prepends an item
               retList[curStackIdx].addLast(input[i][j+1])
            }
            curStackIdx ++
        }
    }
    return retList
}

fun generateMoves(input: List<String>): List<Triple<Int, Int, Int>> {
    val retList: MutableList<Triple<Int, Int, Int>> = mutableListOf()
    for (line in input) {
        val splitLine = line.split(" ")
        val numMoved = splitLine[1].toInt()
        val fromStack = splitLine[3].toInt()
        val toStack = splitLine[5].toInt()
        retList.add(Triple(numMoved, fromStack, toStack))
    }
    return retList
}

fun readAndParseFile(fileName: String): Array<List<String>> {
    val lines = File(fileName).useLines { it.toList() }
    
    var idx = 0
    while (lines[idx] != "") { // Split the input into it's two sections 
        idx++
    }
    return arrayOf(lines.subList(0, idx), lines.subList(idx+1, lines.size))
}
