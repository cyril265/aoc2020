package de.cyril.aoc


private val input = InputReader.readLines("Day1.txt")
    .map { it.toInt() }

fun main() {
    val result = input.asSequence()
        .mapNotNull { firstNumber -> sumIfMatches(firstNumber) }
        .first()
    println(result)
}

fun sumIfMatches(firstNumber: Int): Int? {
    val matchingSecondNumber = input.filter { it != firstNumber }
        .find { secondNumber -> secondNumber + firstNumber == 2020 }

    return if (matchingSecondNumber != null) {
        matchingSecondNumber * firstNumber;
    } else
        null
}