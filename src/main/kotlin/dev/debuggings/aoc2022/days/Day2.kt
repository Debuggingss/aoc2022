package dev.debuggings.aoc2022.days

object Day2 : Day(2) {

    override fun part1() {
        val data = getInput()

        var score = 0

        data.trim().split("\n").forEach {
            val other = it.split(" ")[0].first() - 'A' + 1
            val me = it.split(" ")[1].first() - 'X' + 1

            score += me

            if (other == me) score += 3
            else if (other % 3 + 1 == me) score += 6
        }

        println("\nAnswer: $score")
    }

    override fun part2() {
        val data = getInput()

        var score = 0

        data.trim().split("\n").forEach {
            val other = it.split(" ")[0].first() - 'A' + 1
            val outcome = it.split(" ")[1].first() - 'X' + 1

            score += (outcome - 1) * 3

            when (outcome) {
                1 -> score += (other + 1) % 3 + 1
                2 -> score += other
                3 -> score += other % 3 + 1
            }
        }

        println("\nAnswer: $score")
    }
}
