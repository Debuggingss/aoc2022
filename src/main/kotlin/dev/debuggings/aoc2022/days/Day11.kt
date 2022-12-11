package dev.debuggings.aoc2022.days

object Day11 : Day(11) {

    private val MONKEY_REGEX = """
        ^Monkey (\d):
         {2}Starting items: ([\d, ]+)
         {2}Operation: new = old ([+*]) (\d+|old)
         {2}Test: divisible by (\d+)
         {4}If true: throw to monkey (\d)
         {4}If false: throw to monkey (\d)$
    """.trimIndent().toRegex()

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val monkeys = mutableListOf<Monkey>()

        data.trim().split("\n\n").forEach {
            val (
                monkey,
                itemsString,
                operation,
                operationValue,
                testValue,
                trueValue,
                falseValue,
            ) = MONKEY_REGEX.matchEntire(it)!!.destructured

            val items = itemsString.split(", ").map { i -> i.toLong() }

            monkeys.add(Monkey(
                monkey.toInt(),
                ArrayList(items),
                operation,
                operationValue,
                testValue.toInt(),
                trueValue.toInt(),
                falseValue.toInt()
            ))
        }

        repeat(20) {
            monkeys.forEach {
                val iterator = it.items.iterator()

                while (iterator.hasNext()) {
                    val item = iterator.next()

                    val opValue =
                        if (it.operationValue == "old") item
                        else it.operationValue.toInt()

                    it.items[0] = when (it.operation) {
                        "*" -> item * opValue.toLong()
                        "+" -> item + opValue.toLong()
                        else -> item
                    }

                    it.items[0] /= 3L

                    if (it.items[0] % it.testValue == 0L) {
                        monkeys.find { m -> m.id == it.trueValue }!!.items.add(it.items[0])
                    } else {
                        monkeys.find { m -> m.id == it.falseValue }!!.items.add(it.items[0])
                    }

                    iterator.remove()

                    it.itemsInspected++
                }
            }
        }

        val topInspected = monkeys.map { it.itemsInspected }.sortedDescending()

        println("\nAnswer: ${topInspected[0] * topInspected[1]}")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val monkeys = mutableListOf<Monkey>()

        data.trim().split("\n\n").forEach {
            val (
                monkey,
                itemsString,
                operation,
                operationValue,
                testValue,
                trueValue,
                falseValue,
            ) = MONKEY_REGEX.matchEntire(it)!!.destructured

            val items = itemsString.split(", ").map { i -> i.toLong() }

            monkeys.add(Monkey(
                monkey.toInt(),
                ArrayList(items),
                operation,
                operationValue,
                testValue.toInt(),
                trueValue.toInt(),
                falseValue.toInt()
            ))
        }

        val modulus = monkeys.map { it.testValue.toLong() }.reduce { acc, l -> acc * l }

        repeat(10000) {
            monkeys.forEach {
                val iterator = it.items.iterator()

                while (iterator.hasNext()) {
                    val item = iterator.next()

                    val opValue =
                        if (it.operationValue == "old") item
                        else it.operationValue.toInt()

                    it.items[0] = when (it.operation) {
                        "*" -> item * opValue.toLong()
                        "+" -> item + opValue.toLong()
                        else -> item
                    }

                    it.items[0] %= modulus

                    if (it.items[0] % it.testValue == 0L) {
                        monkeys.find { m -> m.id == it.trueValue }!!.items.add(it.items[0])
                    } else {
                        monkeys.find { m -> m.id == it.falseValue }!!.items.add(it.items[0])
                    }

                    iterator.remove()

                    it.itemsInspected++
                }
            }
        }

        val topInspected = monkeys.map { it.itemsInspected }.sortedDescending()

        println("\nAnswer: ${topInspected[0].toLong() * topInspected[1].toLong()}")
    }

    data class Monkey(
        val id: Int,
        var items: ArrayList<Long>,
        val operation: String,
        val operationValue: String,
        val testValue: Int,
        val trueValue: Int,
        val falseValue: Int,
        var itemsInspected: Int = 0,
    )
}
