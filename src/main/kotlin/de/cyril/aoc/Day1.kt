package de.cyril.aoc


val input = ClassLoader.getSystemResource("Day1.txt").readText()
    .split("\r\n")
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