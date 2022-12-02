fun main() {
    fun part1(input: List<String>): Int {
        
        val caloriesPerElf = mutableListOf<Int>()
        var elfIndex = 0

        input.forEachIndexed { index, s ->
            if (s == "") {
                elfIndex += 1
                return@forEachIndexed
            }
            if (caloriesPerElf.getOrNull(elfIndex) == null) {
                caloriesPerElf.add(elfIndex, 0)
            }
            caloriesPerElf[elfIndex] += s.toInt()
        }

        return caloriesPerElf.maxOf { it }
    }

    fun part2(input: List<String>): Int {
        val caloriesPerElf = mutableListOf<Int>()
        var elfIndex = 0

        input.forEachIndexed { index, s ->
            if (s == "") {
                elfIndex += 1
                return@forEachIndexed
            }
            if (caloriesPerElf.getOrNull(elfIndex) == null) {
                caloriesPerElf.add(elfIndex, 0)
            }
            caloriesPerElf[elfIndex] += s.toInt()
        }

        return caloriesPerElf.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("In01.txt")
    print(part2(testInput))
}
fun <T> List<T>.indicesOf(predicate: (T) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    this.forEachIndexed { index, t ->
        if (predicate(t)) result += index
    }
    return result
}