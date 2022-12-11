fun main() {

    fun part1(input: List<String>): String {

        val stacks = populateInitialState(HashMap(9))

        input
            .map { parseCommand(it) }
            .forEach {
               val (amount, from, to) = it
                repeat(amount) {
                    stacks[from]!!.popAndPushToOther(stacks[to]!!)
                }
        }

        return stacks.values.fold(StringBuilder()) { acc, strings -> acc.append(strings.first()) }.toString()
    }

    fun part2(input: List<String>): String {

        val stacks = populateInitialState(HashMap(9))

        input
            .map { parseCommand(it) }
            .forEach {
                val (amount, from, to) = it
                repeat(amount) {
                    stacks[from]!!.popAndPushLastToOther(stacks[to]!!)
                }
            }

        return stacks.values.fold(StringBuilder()) { acc, strings -> acc.append(strings.first()) }.toString()
    }

    val testInput = readInput("In05.txt")
    print(part1(testInput))

}

private fun ArrayDeque<String>.popAndPushToOther(other: ArrayDeque<String>) {
    val toPush = this.first()
    this.removeFirst()
    other.addFirst(toPush)
}

private fun ArrayDeque<String>.popAndPushLastToOther(other: ArrayDeque<String>) {
    val toPush = this.first()
    this.removeFirst()
    other.addFirst(toPush)
}

private fun parseCommand(command: String): Triple<Int, Int, Int> {
    val amount = command.substringBefore(" from")
        .trim()
        .substringAfter("move ")
        .toInt()
    val stacks = command.substringAfter("from ").filter { it.isDigit() }.map { it.digitToInt() }
    return Triple(amount, stacks[0], stacks[1])
}

private fun populateInitialState(stacks: HashMap<Int, ArrayDeque<String>>): HashMap<Int, ArrayDeque<String>> {
    val s1 = arrayDeque("W", "T", "H", "P", "J", "C", "F")
    val s2 = arrayDeque("H", "B", "J", "Z", "F", "V", "R", "G")
    val s3 = arrayDeque("R", "T", "P", "H")
    val s4 = arrayDeque("T", "H", "P", "N", "S", "Z")
    val s5 = arrayDeque("D", "C", "J", "H", "Z", "F", "V", "N")
    val s6 = arrayDeque("Z", "D", "W", "F", "G", "M", "P")
    val s7 = arrayDeque("P", "D", "J", "S", "W", "Z", "V", "M")
    val s8 = arrayDeque("S", "D", "N")
    val s9 = arrayDeque("M", "F", "S", "Z", "D")

    stacks[1] = s1; stacks[2] = s2; stacks[3] = s3; stacks[4] = s4; stacks[5] = s5;
    stacks[6] = s6; stacks[7] = s7; stacks[8] = s8; stacks[9] = s9

    return stacks
}

private fun arrayDeque(vararg initial: String): ArrayDeque<String> {
    return ArrayDeque<String>().apply { initial.forEach { this.add(it) } }
}