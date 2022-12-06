package dev.debuggings.aoc2022

import dev.debuggings.aoc2022.days.*

val days = mapOf(
    1 to Day1,
    2 to Day2,
    3 to Day3,
    4 to Day4,
    5 to Day5,
    6 to Day6,
)

fun main() {
    print("Enter day: ")
    val day = readln().toInt()

    print("Enter part: ")
    when (readln().toInt()) {
        1 -> days[day]?.part1()
        2 -> days[day]?.part2()
    }
}
