package dev.debuggings.aoc2022.days

import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.sign

object Day9 : Day(9) {

    private val MOVE_REGEX = """^([UDRL]) (\d+)$""".toRegex()

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val head = Point(0, 0)
        val tail = mutableListOf(Point(0, 0))

        data.trim().split("\n").forEach {
            val (move, steps) = MOVE_REGEX.matchEntire(it)!!.destructured

            repeat(steps.toInt()) {
                val prevHeadPos = head.copy()

                when (move) {
                    "U" -> head.y++
                    "D" -> head.y--
                    "R" -> head.x++
                    "L" -> head.x--
                }

                val distance = hypot(
                    head.x - tail.last().x.toDouble(),
                    head.y - tail.last().y.toDouble()
                )

                if (distance > 1.5) tail.add(prevHeadPos)
            }
        }

        println("\nAnswer: ${tail.distinct().size}")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val rope = mutableListOf<Point>()
        val tail = mutableListOf(Point(0, 0))

        repeat(10) {
            rope.add(Point(0, 0))
        }

        data.trim().split("\n").forEach {
            val (move, steps) = MOVE_REGEX.matchEntire(it)!!.destructured

            repeat(steps.toInt()) {
                when (move) {
                    "U" -> rope[0].y++
                    "D" -> rope[0].y--
                    "R" -> rope[0].x++
                    "L" -> rope[0].x--
                }

                for (i in 1..9) {
                    val knot = rope[i]
                    val prevKnot = rope[i - 1]

                    val dX = prevKnot.x - knot.x
                    val dY = prevKnot.y - knot.y

                    if (abs(dX) + abs(dY) > 2) rope[i] = Point(knot.x + dX.sign, knot.y + dY.sign)
                    else if (abs(dX) > 1) rope[i] = Point(knot.x + dX.sign, knot.y)
                    else if (abs(dY) > 1) rope[i] = Point(knot.x, knot.y + dY.sign)

                    if (i == 9) tail.add(knot)
                }
            }
        }

        println("\nAnswer: ${tail.distinct().size}")
    }

    data class Point(var x: Int, var y: Int)
}
