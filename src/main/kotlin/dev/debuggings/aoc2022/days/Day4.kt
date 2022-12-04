package dev.debuggings.aoc2022.days

object Day4 : Day(4) {

    override fun part1() {
        val data = getInput()

        var pairs = 0

        data.trim().split("\n").forEach {
            val (first, second) = it.split(",")

            val (first1, first2) = first.split("-").map { i -> i .toInt() }
            val (second1, second2) = second.split("-").map { i -> i .toInt() }

            if (
                (first1 <= second1 && first2 >= second2) ||
                (first1 >= second1 && first2 <= second2)
            ) pairs++
        }

        println("\nAnswer: $pairs")
    }

    override fun part2() {
        val data = getInput()

        var pairs = 0

        data.trim().split("\n").forEach {
            val (first, second) = it.split(",")

            val (first1, first2) = first.split("-").map { i -> i .toInt() }
            val (second1, second2) = second.split("-").map { i -> i .toInt() }

            if (
                (first1..first2).intersect(second1..second2).isNotEmpty()
            ) pairs++
        }

        println("\nAnswer: $pairs")
    }
}
