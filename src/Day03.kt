fun main() {
    fun part1(input: List<String>): Int {

        var acc = 0
        val lowercases = ('a'..'z')
        val uppercases = ('A'..'Z')
        val lowercasesPriorities = (1..26)
        val uppercasesPriorities = (27..52)
        lowercases.associateWith { lowercasesPriorities }
        val lower = lowercases.zip(lowercasesPriorities)
        val upper = uppercases.zip(uppercasesPriorities)

        input.forEach { line ->
            val compartments = line.splitInHalf()
            val first = compartments.first().toCharArray().toSet()
            val second = compartments[1].toCharArray().toSet()
            val commonChars = first.intersect(second)
            commonChars.forEach { c ->
                if (c in lowercases){
                    acc += lower.find { it.first == c }!!.second
                } else if (c in uppercases) {
                    acc += upper.find { it.first == c }!!.second
                }
            }
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

        val priorities = ('a'..'z').zip( (1..26))
            .toMap(hashMapOf()).apply { putAll(('A'..'Z').zip((27..52)).toMap()) }

        return typesPerGroup.sumOf { priorities[it]!! }
    }

    val testInput = readInput("In03.txt")
    print(part2(testInput))
}

fun String.splitInHalf() : List<String> {
    return listOf(
        this.substring(0, this.length / 2),
        this.substring(this.length / 2, this.length)
    )
}