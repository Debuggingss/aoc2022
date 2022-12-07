package dev.debuggings.aoc2022

import dev.debuggings.aoc2022.days.*

val days = mapOf(
    1 to Day1,
    2 to Day2,
    3 to Day3,
    4 to Day4,
    5 to Day5,
    6 to Day6,
    7 to Day7,
)

fun main() {
    print("Enter day: ")
    val day = readln().toInt()

    print("Enter part: ")
    val part = readln().toInt()

    print("Debug (Y/N): ")
    val debug = readln().lowercase() == "y"

    if (debug) println("--- RUNNING WITH TEST INPUT ---")

    when (part) {
        1 -> days[day]?.part1(debug)
        2 -> days[day]?.part2(debug)
    }
}
