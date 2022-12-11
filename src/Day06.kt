fun main() {

    fun part1(inp: List<String>): Int {
        val input: String = inp.first()

        val cache = LinkedHashSet<Char>()

        (4..input.length).forEach {
            cache.clear()
            cache.addAll(input.substring(it -4, it).toList())
            if (cache.size == 4) return it
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("In06.txt")
    print(part1(testInput))
}
