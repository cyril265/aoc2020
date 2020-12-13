package de.cyril.aoc


fun main() {
    val lines: List<List<Boolean>> = InputReader.readLines("Day3.txt")
        .map { line ->
            line.map { it == '#' }
        }
    val columnCount = lines[0].size

    var columnIndex = 0;
    var lineIndex = 0;

    val treeCount = (1 until lines.size).count {
        columnIndex = (columnIndex + 3) % columnCount
        lineIndex++
        lines[lineIndex][columnIndex]
    }

    println(treeCount)

}