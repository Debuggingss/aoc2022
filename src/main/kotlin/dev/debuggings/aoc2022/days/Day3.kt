package dev.debuggings.aoc2022.days

object Day3 : Day(3) {

    override fun part1() {
        val data = getInput()

        var sum = 0

        data.trim().split("\n").forEach {
            val first = it.substring(0, it.length / 2)
            val second = it.substring(it.length / 2)

            first.toCharArray().distinct().forEach { item ->
                if (second.contains(item)) {
                    sum += if (item.isUpperCase()) {
                        item.code - 'A'.code + 27
                    } else {
                        item.code - 'a'.code + 1
                    }
                }
            }
        }

        println("\nAnswer: $sum")
    }

    override fun part2() {
        val data = getInput()

        var sum = 0

        data.trim().split("\n").chunked(3).forEach { group ->
            val (first, second, third) = group

            first.toCharArray().distinct().forEach { item ->
                if (second.contains(item) && third.contains(item)) {
                    sum += if (item.isUpperCase()) {
                        item.code - 'A'.code + 27
                    } else {
                        item.code - 'a'.code + 1
                    }
                }
            }
        }

        println("\nAnswer: $sum")
    }
}
