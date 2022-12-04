fun main() {
    fun part1(input: List<String>): Int {

        return input.sumOf {
            val (opp, me) = it.split(" ")
            val oppM = OpponentMove.valueOf(opp)
            val meM = MyMove.valueOf(me)
            score(oppM, meM)
        }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("In02.txt")
    print(part1(testInput))
}

enum class OpponentMove {
    A, B, C
}

enum class MyMove {
    X, Y, Z
}

fun score(opponentMove: OpponentMove, myMove: MyMove): Int {

    if (Move.fromMe(myMove) == Move.fromOpponent(opponentMove)) {
        return 3 + Move.fromMe(myMove).points
    }

    return if (Move.winningPairs.contains(Pair(Move.fromMe(myMove), Move.fromOpponent(opponentMove)))) {
        6 + Move.fromMe(myMove).points
    } else {
        Move.fromMe(myMove).points
    }
}

enum class Move(val points: Int) {
    Paper(2),
    Scissors(3),
    Rock(1);

    companion object {

        val winningPairs = listOf(
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
    }
}

