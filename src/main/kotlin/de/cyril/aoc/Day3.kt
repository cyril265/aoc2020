package de.cyril.aoc

private val lines: List<List<Boolean>> = InputReader.readLines("day3.txt")
    .map { line ->
        line.map { it == '#' }
    }

private val columnCount = lines[0].size


fun main() {
    val result = countTrees(1, 1) *
            countTrees(3, 1) *
            countTrees(5, 1) *
            countTrees(7, 1) *
            countTrees(1, 2)

    println(result)
}

private fun countTrees(right: Int, down: Int): Long {
    var columnIndex = 0;
    var lineIndex = 0;

    val treeCount = (1 until lines.size).count {
        columnIndex = (columnIndex + right) % columnCount
        lineIndex += down

        if (lineIndex > lines.size - 1) {
            false
        } else {
            lines[lineIndex][columnIndex]
        }
    }
    return treeCount.toLong()
}
