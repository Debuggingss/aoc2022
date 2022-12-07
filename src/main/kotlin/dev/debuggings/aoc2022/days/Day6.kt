package dev.debuggings.aoc2022.days

object Day6 : Day(6) {

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val buffer = mutableListOf<String>()
        var processed = 0

        data.split("").drop(1).forEachIndexed { i, c ->
            if (processed != 0) return@forEachIndexed

            buffer.add(c)

            if (buffer.distinct().size == 4) processed = i + 1
            if (buffer.size == 4) buffer.removeFirst()
        }

        println("\nAnswer: $processed")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val buffer = mutableListOf<String>()
        var processed = 0

        data.split("").drop(1).forEachIndexed { i, c ->
            if (processed != 0) return@forEachIndexed

            buffer.add(c)

            if (buffer.distinct().size == 14) processed = i + 1
            if (buffer.size == 14) buffer.removeFirst()
        }

        println("\nAnswer: $processed")
    }
}
