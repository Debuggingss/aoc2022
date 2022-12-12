package dev.debuggings.aoc2022.days

object Day12 : Day(12) {

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val grid = mutableListOf<MutableList<GridElement>>()
        val path = mutableListOf<GridElement>()

        data.trim().split("\n").forEachIndexed { i, r ->
            val row = mutableListOf<GridElement>()

            r.toCharArray().forEachIndexed { i2, c ->
                val char = when (c) {
                    'S' -> -1
                    'E' -> 26
                    else -> c.code - 'a'.code
                }

                val elem = GridElement(i2, i, char, c)

                if (c == 'S') path.add(elem)

                row.add(elem)
            }

            grid.add(row)
        }

        val width = grid[0].size
        val height = grid.size

        val beenTo = mutableListOf<GridElement>()

        while (path.isNotEmpty()) {
            val currentElement = path.removeFirst()

            if (currentElement.char == 'E') {
                println("\nAnswer: ${currentElement.counter}")
                break
            }

            for ((xOff, yOff) in listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))) {
                val newPos = Pair(currentElement.x + xOff, currentElement.y + yOff)

                if (newPos.first < 0 || newPos.second < 0 || newPos.first >= width || newPos.second >= height) continue

                val newElement = grid[newPos.second][newPos.first]

                if (beenTo.contains(newElement)) continue
                if (newElement.value - grid[currentElement.y][currentElement.x].value > 1) continue

                newElement.counter = currentElement.counter + 1

                path.add(newElement)
                beenTo.add(newElement)
            }
        }
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val grid = mutableListOf<MutableList<GridElement>>()
        val path = mutableListOf<Pair<GridElement, Int>>()

        data.trim().split("\n").forEachIndexed { i, r ->
            val row = mutableListOf<GridElement>()

            r.toCharArray().forEachIndexed { i2, c ->
                val char = when (c) {
                    'S' -> -1
                    'E' -> 26
                    else -> c.code - 'a'.code
                }

                val elem = GridElement(i2, i, char, c)

                if (c == 'S') path.add(Pair(elem, 0))
                else if (c == 'a') path.add(Pair(elem, 0))

                row.add(elem)
            }

            grid.add(row)
        }

        val width = grid[0].size
        val height = grid.size

        val beenTo = mutableListOf<GridElement>()

        while (path.isNotEmpty()) {
            val (currentElement, steps) = path.removeFirst()

            if (currentElement.char == 'E') {
                println("\nAnswer: $steps")
                break
            }

            for ((xOff, yOff) in listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))) {
                val newPos = Pair(currentElement.x + xOff, currentElement.y + yOff)

                if (newPos.first < 0 || newPos.second < 0 || newPos.first >= width || newPos.second >= height) continue

                val newElement = grid[newPos.second][newPos.first]

                if (beenTo.contains(newElement)) continue
                if (newElement.value - grid[currentElement.y][currentElement.x].value > 1) continue

                path.add(Pair(newElement, steps + 1))
                beenTo.add(newElement)
            }
        }
    }

    data class GridElement(
        val x: Int,
        val y: Int,
        val value: Int,
        val char: Char,
        var counter: Int = 0,
    )
}
