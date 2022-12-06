fun main() {

    val priorities = ('a'..'z').zip( (1..26))
        .toMap(hashMapOf()).apply { putAll(('A'..'Z').zip((27..52)).toMap()) }

    fun part1(input: List<String>): Int {

        var acc = 0

        input.forEach { line ->
            val compartments = line.splitInHalf()
            val first = compartments.first().toCharArray().toSet()
            val second = compartments[1].toCharArray().toSet()
            val commonChars = first.intersect(second)
            commonChars.forEach { c -> acc += priorities[c]!! }
        }

        return acc
    }

    fun part2(input: List<String>): Int {

        val typesPerGroup = mutableListOf<Char>()

        val ruckackArrays: List<List<CharArray>> = input
            .windowed(3, step = 3) { strings ->
                strings.map { it.toCharArray() }
                    .sortedBy { it.size }
            }

        ruckackArrays.forEach {

            try {
                it.first().first { smallListChar ->
                    (smallListChar in it[1]) && (smallListChar in it[2])
                }.let { typeFound -> typesPerGroup.add(typeFound) }
            } catch (e: NoSuchElementException) { }
        }

        return typesPerGroup.sumOf { priorities[it]!! }
    }

    val testInput = readInput("In03.txt")
    print(part1(testInput))
}

fun String.splitInHalf() : List<String> {
    return listOf(
        this.substring(0, this.length / 2),
        this.substring(this.length / 2, this.length)
    )
}