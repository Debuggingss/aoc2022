package dev.debuggings.aoc2022.days

import dev.debuggings.aoc2022.utils.Utils

abstract class Day(private val day: Int) {

    fun getInput(): String {
        println("Getting input for day $day...")
        return Utils.getInput(day)
    }

    abstract fun part1()
    abstract fun part2()
}
