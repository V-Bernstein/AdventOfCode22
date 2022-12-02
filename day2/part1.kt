import java.io.File

public class Part1Solution
  
    val OPPONENT_ROCK = 'A'
    val OPPONENT_PAPER = 'B'
    val OPPONENT_SCISSORS = 'C'

    val MY_ROCK = 'X'
    val MY_PAPER = 'Y'
    val MY_SCISSORS = 'Z'

fun main(args: Array<String>) {
    val input = readFile("input.txt")
    var sum: Int = 0
    for (line in input) {
        val moves = line.split(" ")
        sum += calcScore(moves[0].get(0), moves[1].get(0))
    }
    println(sum)
}

fun calcScore(oppMove: Char, myMove: Char): Int {
    var score: Int = (myMove - MY_ROCK) + 1 // Calculate score from my move
    if (myMove - MY_ROCK == oppMove - OPPONENT_ROCK) { // draw
        score += 3
    } else if (didIWin(oppMove, myMove)) {
        score += 6
    }
    return score
}

fun didIWin(oppMove: Char, myMove: Char): Boolean {
    if (oppMove == OPPONENT_ROCK) {
        return (myMove == MY_PAPER)
    } else if (oppMove == OPPONENT_PAPER) {
        return (myMove == MY_SCISSORS)
    } else if (oppMove == OPPONENT_SCISSORS) {
        return (myMove == MY_ROCK)
    }

    println("Unexpected case: " + oppMove + myMove)
    return false
}

fun readFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
