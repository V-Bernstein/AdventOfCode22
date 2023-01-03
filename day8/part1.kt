import java.io.File

public class Part1Solution

class Tree(val height: Int, var isVisible: Boolean = false) { }

fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readFile(fileName)
    val forest = parseInput(input)

    for (y in 0 until forest[0].size) { // Iterate through columns
        // Is tree visible from the north?
        var tallest = -1
        for (i in 0 until forest.size) {
            if (forest[i][y].height > tallest) {
                tallest = forest[i][y].height
                forest[i][y].isVisible = true            
            }
        }
        
        tallest = -1
        for (i in forest.size - 1 downTo 0) {
            if (forest[i][y].height > tallest) {
                tallest = forest[i][y].height
                forest[i][y].isVisible = true
            }
        }
    }

    for (x in 0 until forest.size) { // Iterate through rows
        // Is tree visible from the east?
        var tallest = -1
        for (i in forest[x].size - 1 downTo 0) {
            if (forest[x][i].height > tallest) {
                tallest = forest[x][i].height
                forest[x][i].isVisible = true
            }
        }

        // Is tree visible from the west?
        tallest = -1
        for (i in 0 until forest[x].size) {
            if (forest[x][i].height > tallest) {
                tallest = forest[x][i].height
                forest[x][i].isVisible = true
            }
        }
    }

    var visibleTrees = 0
    for (line in forest) {
        for (tree in line) {
           if (tree.isVisible) {
             visibleTrees++
           }
        }
    }
    print(visibleTrees)
}

fun parseInput(input: List<String>): List<List<Tree>> {
    var retList: MutableList<MutableList<Tree>> = mutableListOf()
    for (line in input) {
        val list: MutableList<Tree> = mutableListOf() 
        for (ch in line) {
            list.add(Tree(ch.digitToInt()))
        }
        retList.add(list)
    }
    return retList
}

fun readFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
