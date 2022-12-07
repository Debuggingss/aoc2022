package dev.debuggings.aoc2022.days

object Day7 : Day(7) {

    private val CD_REGEX = """^\$ cd (.+)$""".toRegex()
    private val FILE_REGEX = """^(\d+) ([\w.]+)$""".toRegex()

    override fun part1(debug: Boolean) {
        val data = getInput(debug)

        val dirs = hashMapOf<String, Int>()
        var path = ""

        data.trim().split("\n").drop(1).forEach {
            if (it.matches(CD_REGEX)) {
                val (dir) = CD_REGEX.matchEntire(it)!!.destructured

                if (dir == "..") path = path.dropLastWhile { c -> c != '/' }.dropLast(1)
                else {
                    path += "/$dir"
                    dirs[path] = 0
                }
            }
            else if (it.matches(FILE_REGEX)) {
                val (size, _) = FILE_REGEX.matchEntire(it)!!.destructured

                val split = path.split("/")
                val subpaths = (1..split.size).map { s -> split.take(s).joinToString("/") }.drop(1)

                subpaths.forEach { s ->
                    dirs[s] = dirs[s]!! + size.toInt()
                }

                if (!dirs.containsKey("/")) dirs["/"] = 0
                dirs["/"] = dirs["/"]!! + size.toInt()
            }
        }

        val smallDirs = dirs.filterValues { it <= 100000 } as HashMap<String, Int>

        println("\nAnswer: ${smallDirs.values.sum()}")
    }

    override fun part2(debug: Boolean) {
        val data = getInput(debug)

        val dirs = hashMapOf<String, Int>()
        var path = ""

        data.trim().split("\n").drop(1).forEach {
            if (it.matches(CD_REGEX)) {
                val (dir) = CD_REGEX.matchEntire(it)!!.destructured

                if (dir == "..") path = path.dropLastWhile { c -> c != '/' }.dropLast(1)
                else {
                    path += "/$dir"
                    dirs[path] = 0
                }
            }
            else if (it.matches(FILE_REGEX)) {
                val (size, _) = FILE_REGEX.matchEntire(it)!!.destructured

                val split = path.split("/")
                val subpaths = (1..split.size).map { s -> split.take(s).joinToString("/") }.drop(1)

                subpaths.forEach { s ->
                    dirs[s] = dirs[s]!! + size.toInt()
                }

                if (!dirs.containsKey("/")) dirs["/"] = 0
                dirs["/"] = dirs["/"]!! + size.toInt()
            }
        }

        val toFree = 30_000_000 - (70_000_000 - dirs["/"]!!)
        val smallestDir = dirs.filterValues { it >= toFree }.values.minOf { it }

        println("\nAnswer: $smallestDir")
    }
}
