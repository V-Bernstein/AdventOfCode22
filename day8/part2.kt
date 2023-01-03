import java.io.File

public class Part2Solution

class Tree(val height: Int, var scenicScore: Int = 1) { }

fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readFile(fileName)
    val forest = parseInput(input)

    var maxScenicScore = 0
    for (x in 0 until forest.size) {
        for (y in 0 until forest[x].size) {
            val tree = forest[x][y]
            tree.scenicScore = calcScenicScoreNorth(forest, x, y) *
                               calcScenicScoreEast(forest, x, y) *
                               calcScenicScoreSouth(forest, x, y) *
                               calcScenicScoreWest(forest, x, y)
            if (tree.scenicScore > maxScenicScore) {
                maxScenicScore = tree.scenicScore
            }
        }
    }
    print(maxScenicScore)
}

fun calcScenicScoreNorth(forest: List<List<Tree>>, x: Int, y: Int): Int {
    if (x == 0) {
        return 0
    }

    var scenicScore = 0
    val treeHeight = forest[x][y].height
    for (i in x - 1 downTo 0) {
        scenicScore ++
        if (treeHeight <= forest[i][y].height) { // Our view is blocked
            break
        }
    }

    return scenicScore
}

fun calcScenicScoreEast(forest: List<List<Tree>>, x: Int, y: Int): Int {
    if (y == forest[x].size - 1) {
        return 0
    }

    var scenicScore = 0
    val treeHeight = forest[x][y].height
    for (i in y + 1 until forest[x].size) {
        scenicScore ++
        if (treeHeight <= forest[x][i].height) { // Our view is blocked
            break
        }
    }

    return scenicScore
}

fun calcScenicScoreSouth(forest: List<List<Tree>>, x: Int, y: Int): Int {
    if (x == forest.size - 1) {
        return 0
    }

    var scenicScore = 0
    val treeHeight = forest[x][y].height
    for (i in x + 1 until forest.size) {
        scenicScore ++
        if (treeHeight <= forest[i][y].height) { // Our view is blocked 
            break
        }
    }

    return scenicScore
}

fun calcScenicScoreWest(forest: List<List<Tree>>, x: Int, y: Int): Int {
    if (y == 0) {
        return 0
    }

    var scenicScore = 0
    val treeHeight = forest[x][y].height
    for (i in y - 1 downTo 0) {
        scenicScore ++
        if (treeHeight <= forest[x][i].height) { // Our view is blocked
            break
        }
    }

    return scenicScore
}

fun parseInput(input: List<String>): List<List<Tree>> {
    var retList: MutableList<MutableList<Tree>> = mutableListOf()
    for (line in input) {
        val list: MutableList<Tree> = mutableListOf() 
        for (ch in line) {
            // TODO: This could return a List<IntArray>, since we don't need a full class for just the height
            list.add(Tree(ch.digitToInt()))
        }
        retList.add(list)
    }
    return retList
}

fun readFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
