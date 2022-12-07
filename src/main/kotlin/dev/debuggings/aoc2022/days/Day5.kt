package dev.debuggings.aoc2022.days

object Day5 : Day(5) {

    private val BOX_REGEX = """\[(\w)]| {4}""".toRegex()
    private val MOVE_REGEX = """move (\d+) from (\d+) to (\d+)""".toRegex()

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        var stack: MutableList<MutableList<String>> = mutableListOf()

        data.trimEnd().split("\n").filter { BOX_REGEX.containsMatchIn(it) }.forEach {
            val boxes = BOX_REGEX.findAll(it).map { b -> b.groupValues[1] }
            stack.add(boxes.toMutableList())
        }

        stack = List(stack[0].size) { i -> List(stack.size) { i2 -> stack[i2][i] }.toMutableList() }.toMutableList()
        stack = stack.map { it.filter { s -> s.isNotEmpty() }.toMutableList() }.toMutableList()

        data.trim().split("\n").filter { MOVE_REGEX.containsMatchIn(it) }.forEach {
            val (count, from, to) = MOVE_REGEX.matchEntire(it)!!.destructured

            repeat(count.toInt()) {
                val box = stack[from.toInt() - 1].first()
                stack[from.toInt() - 1].removeFirst()
                stack[to.toInt() - 1].add(0, box)
            }
        }

        println("\nAnswer: ${stack.joinToString("") { it.first() }}")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        var stack: MutableList<MutableList<String>> = mutableListOf()

        data.trimEnd().split("\n").filter { BOX_REGEX.containsMatchIn(it) }.forEach {
            val boxes = BOX_REGEX.findAll(it).map { b -> b.groupValues[1] }
            stack.add(boxes.toMutableList())
        }

        stack = List(stack[0].size) { i -> List(stack.size) { i2 -> stack[i2][i] }.toMutableList() }.toMutableList()
        stack = stack.map { it.filter { s -> s.isNotEmpty() }.toMutableList() }.toMutableList()

        data.trim().split("\n").filter { MOVE_REGEX.containsMatchIn(it) }.forEach {
            val (count, from, to) = MOVE_REGEX.matchEntire(it)!!.destructured

            for (i in 0 until count.toInt()) {
                val box = stack[from.toInt() - 1][count.toInt() - 1 - i]
                stack[from.toInt() - 1].removeAt(count.toInt() - 1 - i)
                stack[to.toInt() - 1].add(0, box)
            }
        }

        println("\nAnswer: ${stack.joinToString("") { it.first() }}")
    }
}
