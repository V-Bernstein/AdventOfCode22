import java.io.File
import java.util.ArrayDeque
import java.math.BigInteger

public class Part2Solution

class Node(val name: String, val isDir: Boolean, var size: BigInteger = 0.toBigInteger(), val children: MutableList<Node> = mutableListOf()) { }

val UNUSED_SPACE_NEEDED: BigInteger = 30_000_000.toBigInteger()
val TOTAL_SIZE: BigInteger = 70_000_000.toBigInteger()
  
fun main(args: Array<String>) {
    val fileName = args[0]
    val input = readAndParseFile(fileName)
    val root = parseTree(input)
    val curFreeSpace = TOTAL_SIZE - root.size
    var minDeletionSize = root.size
    val stack = ArrayDeque<Node>() // For DFS 
    stack.addLast(root)
    while (!stack.isEmpty()) {
        val curNode = stack.removeLast()
        for (child in curNode.children) {
           stack.addLast(child) 
        }

        if (curNode.isDir) {
            val possibleSpace = curFreeSpace + curNode.size
            if (possibleSpace >= UNUSED_SPACE_NEEDED) {
                if (curNode.size < minDeletionSize) {
                    minDeletionSize = curNode.size
                }
            }
        }
    }
    println(minDeletionSize)
}

fun parseTree(outputs: List<String>): Node {
    val root = Node("/", true)
    var curNode = root
    val stack = ArrayDeque<Node>() // Holds previous nodes so we can cd ..
    for (idx in 1 until outputs.size) { // Skip $ cd /
        val coms = outputs[idx].split(" ")
        if (coms[0] == "$") { // This is a command
            if (coms[1] == "cd") {
                if (coms[2] == "..") {
                    val justVisited = curNode
                    curNode = stack.removeLast()
                    // Update sums
                    curNode.size += justVisited.size
                } else {
                    stack.addLast(curNode)
                    curNode = getCorrectChildNode(coms[2], curNode.children)
                }
            }
        } else {
            // This is a file/dir, add as a node
            var isDir = false
            var size: BigInteger = 0.toBigInteger()
            if (coms[0] == "dir") {
                isDir = true
            } else { // This is a file with a size
                size = coms[0].toBigInteger()
            }
            val newNode = Node(coms[1], isDir)
            newNode.size = size
            curNode.children.add(newNode)
            curNode.size += newNode.size // Doesn't include directory child sizes
        }
    }
    // Update sums for un-CD'd dirs
    while (!stack.isEmpty()) {
        val justVisited = curNode
        curNode = stack.removeLast()
        curNode.size += justVisited.size
    }
    return root
}

fun getCorrectChildNode(target: String, children: List<Node>): Node {
    for (child in children) {
        if (child.name == target) {
            return child
        }
    }
    println("No child found")
    return Node("ERROR", false)
}

fun readAndParseFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
