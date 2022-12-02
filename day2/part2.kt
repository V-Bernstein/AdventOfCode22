import java.io.File

public class Part1Solution
    val OPPONENT_ROCK = 'A'
    val OPPONENT_PAPER = 'B'
    val OPPONENT_SCISSORS = 'C'

    val ROCK_SCORE = 1
    val PAPER_SCORE = 2
    val SCISSORS_SCORE = 3

    val I_LOSE = 'X'
    val I_DRAW = 'Y'
    val I_WIN = 'Z'

fun main(args: Array<String>) {
    val fileName = args.get(0)
    val input = readFile(fileName)
    var sum: Int = 0
    for (line in input) {
        val moves = line.split(" ")
        sum += calcScore(moves[0].get(0), moves[1].get(0))
    }
    println(sum)
}

fun calcScore(oppMove: Char, outcome: Char): Int {
    var score: Int = calcMyMoveScore(oppMove, outcome)
    if (outcome == I_DRAW) {
        score += 3
    } else if (outcome == I_WIN) {
        score += 6
    }
    return score
}

fun calcMyMoveScore(oppMove: Char, outcome: Char): Int {
    if (oppMove == OPPONENT_ROCK) {
        if (outcome == I_LOSE) {
            return SCISSORS_SCORE
        } else if (outcome == I_DRAW) {
            return ROCK_SCORE
        } else {
            return PAPER_SCORE
        }
    } else if (oppMove == OPPONENT_PAPER) {
        if (outcome == I_LOSE) {
            return ROCK_SCORE 
        } else if (outcome == I_DRAW) {
            return PAPER_SCORE
        } else {
            return SCISSORS_SCORE
        }
    } else if (oppMove == OPPONENT_SCISSORS) {
        if (outcome == I_LOSE) {
            return PAPER_SCORE
        } else if (outcome == I_DRAW) {
            return SCISSORS_SCORE
        } else {
            return ROCK_SCORE
        }
    }

    println("Unexpected case: " + oppMove + outcome)
    return 0 
}

fun readFile(fileName: String): List<String> {
    return File(fileName).useLines { it.toList() }
}
