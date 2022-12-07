package dev.debuggings.aoc2022.days

import dev.debuggings.aoc2022.utils.Utils

abstract class Day(private val day: Int) {

    fun getInput(debug: Boolean): String {
        println("Getting input for day $day...")

        if (debug) return Utils.getTestInput(day)
        return Utils.getInput(day)
    }

    abstract fun part1(debug: Boolean)
    abstract fun part2(debug: Boolean)
}
