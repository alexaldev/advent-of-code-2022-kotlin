fun main() {
    fun part1(input: List<String>): Int {

        return input.sumOf {
            val (opp, me) = it.split(" ")
            val oppM = OpponentMove.valueOf(opp)
            val meM = MyMove.valueOf(me)
            scorePart1(oppM, meM)
        }
    }

    fun part2(input: List<String>): Int {

        return input.sumOf {
            val (opp, result) = it.split(" ")
            val oppM = OpponentMove.valueOf(opp)
            val resultMv = MovesResult.fromMyMove(MyMove.valueOf(result))
            scorePart2(oppM, resultMv)
        }
    }

    val testInput = readInput("In02.txt")
    print(part2(testInput))
}

enum class OpponentMove {
    A, B, C
}

enum class MyMove {
    X, Y, Z
}

fun scorePart1(opponentMove: OpponentMove, myMove: MyMove): Int {

    if (Move.fromMe(myMove) == Move.fromOpponent(opponentMove)) {
        return 3 + Move.fromMe(myMove).points
    }

    return if (Move.winsTo.contains(Pair(Move.fromMe(myMove), Move.fromOpponent(opponentMove)))) {
        6 + Move.fromMe(myMove).points
    } else {
        Move.fromMe(myMove).points
    }
}

fun scorePart2(opponentMove: OpponentMove, expectedResult: MovesResult): Int {
    return when(expectedResult) {
        MovesResult.Win -> Move.losesToMap[Move.fromOpponent(opponentMove)]!!.points + 6
        MovesResult.Lose -> Move.winsToMap[Move.fromOpponent(opponentMove)]!!.points
        MovesResult.Draw -> Move.fromOpponent(opponentMove).points + 3
    }
}

enum class Move(val points: Int) {
    Paper(2),
    Scissors(3),
    Rock(1);

    companion object {


        // ----- Part 1 -----------
        val winsTo = listOf(
            Paper to Rock,
            Rock to Scissors,
            Scissors to Paper
        )

        val winsToMap = mapOf(
            Paper to Rock,
            Rock to Scissors,
            Scissors to Paper
        )

        fun fromOpponent(opponentMove: OpponentMove): Move {
            return when(opponentMove) {
                OpponentMove.A -> Rock
                OpponentMove.B -> Paper
                OpponentMove.C -> Scissors
            }
        }
        fun fromMe(myMove: MyMove): Move {
            return when(myMove) {
                MyMove.X -> Rock
                MyMove.Y -> Paper
                MyMove.Z -> Scissors
            }
        }

        // ------- Part 2 --------

        val losesToMap = mapOf(
            Paper to Scissors,
            Rock to Paper,
            Scissors to Rock
        )
    }
}

enum class MovesResult {
    Win, Lose, Draw;

    companion object {
        fun fromMyMove(myMove: MyMove): MovesResult {
            return when(myMove) {
                MyMove.X -> Lose
                MyMove.Y -> Draw
                MyMove.Z -> Win
            }
        }
    }
}