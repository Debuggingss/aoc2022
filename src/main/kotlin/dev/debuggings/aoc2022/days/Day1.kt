package dev.debuggings.aoc2022.days

object Day1 : Day(1) {

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val calories = mutableListOf<Int>()
        var currentCalories = 0

        data.split("\n").forEach {
            if (it.isEmpty()) {
                calories.add(currentCalories)
                currentCalories = 0
            } else {
                currentCalories += it.toInt()
            }
        }

        println("\nAnswer: ${calories.max()}")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val calories = mutableListOf<Int>()
        var currentCalories = 0

        data.split("\n").forEach {
            if (it.isEmpty()) {
                calories.add(currentCalories)
                currentCalories = 0
            } else {
                currentCalories += it.toInt()
            }
        }

        val sorted = calories.sorted().reversed()
        println("\nAnswer: ${sorted[0] + sorted[1] + sorted[2]}")
    }
}
