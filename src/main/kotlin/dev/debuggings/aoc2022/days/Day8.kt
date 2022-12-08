package dev.debuggings.aoc2022.days

import kotlin.math.max

object Day8 : Day(8) {

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val rows = data.trim().split("\n")
        val columns = List(rows[0].length) { i -> List(rows.size) { i2 -> rows[i2][i] } }

        val width = columns.size
        val height = rows.size

        var visible = 0

        for (row in 0 until height) {
            for (tree in 0 until width) {
                if (row == 0 || row == height - 1 || tree == 0 || tree == width - 1) {
                    visible++
                    continue
                }

                val t = rows[row][tree].toString().toInt()

                if (
                    rows[row].slice(0 until tree).all { it.toString().toInt() < t } ||
                    rows[row].slice((tree + 1) until width).all { it.toString().toInt() < t } ||
                    columns[tree].slice(0 until row).all { it.toString().toInt() < t } ||
                    columns[tree].slice((row + 1) until height).all { it.toString().toInt() < t }
                ) visible++
            }
        }

        println("\nAnswer: $visible")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val rows = data.trim().split("\n")
        val columns = List(rows[0].length) { i -> List(rows.size) { i2 -> rows[i2][i] } }

        val width = columns.size
        val height = rows.size

        var highest = 0

        for (row in 0 until height) {
            for (tree in 0 until width) {
                if (row == 0 || row == height - 1 || tree == 0 || tree == width - 1) continue

                val t = rows[row][tree].toString().toInt()

                var up = 0

                run loop@ {
                    columns[tree].slice((row - 1) downTo 0).forEach {
                        up++
                        if (it.toString().toInt() >= t) return@loop
                    }
                }

                var down = 0

                run loop@ {
                    columns[tree].slice((row + 1) until height).forEach {
                        down++
                        if (it.toString().toInt() >= t) return@loop
                    }
                }

                var left = 0

                run loop@ {
                    rows[row].slice((tree - 1) downTo 0).forEach {
                        left++
                        if (it.toString().toInt() >= t) return@loop
                    }
                }

                var right = 0

                run loop@ {
                    rows[row].slice((tree + 1) until width).forEach {
                        right++
                        if (it.toString().toInt() >= t) return@loop
                    }
                }

                highest = max(highest, up * down * left * right)
            }
        }

        println("\nAnswer: $highest")
    }
}
