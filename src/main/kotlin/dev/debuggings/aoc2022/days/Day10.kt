package dev.debuggings.aoc2022.days

import kotlin.math.abs

object Day10 : Day(10) {

    private val INSTRUCTION_REGEX = """^(addx|noop) ?([-\d]+)?$""".toRegex()

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        var strength = 0
        var cycle = 0
        var x = 1

        fun runCycle() {
            cycle++
            if (cycle % 40 == 20) strength += cycle * x
        }

        data.trim().split("\n").forEach {
            val (instruction, value) = INSTRUCTION_REGEX.matchEntire(it)!!.destructured

            runCycle()

            if (instruction == "addx") {
                runCycle()
                x += value.toInt()
            }
        }

        println("\nAnswer: $strength")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        var cycle = 0
        var x = 1

        fun runCycle() {
            if (abs(cycle % 40 - x) <= 1) print("⬛")
            else print("⬜")

            cycle++

            if (cycle % 40 == 0) println()
        }

        data.trim().split("\n").forEach {
            val (instruction, value) = INSTRUCTION_REGEX.matchEntire(it)!!.destructured

            runCycle()

            if (instruction == "addx") {
                runCycle()
                x += value.toInt()
            }
        }
    }
}
