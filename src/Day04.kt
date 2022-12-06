fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(",")
            .map { range -> range.split("-") } }
            .map { it.hasARangeIncludedInOther() }
            .count { it }
    }

    fun part2(input: List<String>): Int {

        return input
            .map {
                it.split(",")
                    .map { range -> range.split("-") }
            }.count { it.overlaps() }
    }

    val testInput = readInput("In04.txt")
    print(part2(testInput))
}

fun List<List<String>>.hasARangeIncludedInOther(): Boolean {
    val thisToInt = this.map { it.map { s -> s.toInt() } }

    val firstRange = thisToInt.first().first()..thisToInt.first()[1]
    val secondRange = thisToInt[1].first()..thisToInt[1][1]

    val firstInSecond = (firstRange.first >= secondRange.first) && (firstRange.last <= secondRange.last)
    val secondInFirst = (firstRange.first <= secondRange.first) && (firstRange.last >= secondRange.last)

    return firstInSecond || secondInFirst
}

fun List<List<String>>.overlaps(): Boolean {
    val thisToInt = this.map { it.map { s -> s.toInt() } }

    val firstRange = thisToInt.first().first()..thisToInt.first()[1]
    val secondRange = thisToInt[1].first()..thisToInt[1][1]

    return firstRange.overlaps(secondRange)
}

fun IntRange.overlaps(other: IntRange): Boolean {
    return this.any { it in other }
}
