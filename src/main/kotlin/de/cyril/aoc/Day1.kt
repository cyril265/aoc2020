package de.cyril.aoc


private val input = InputReader.readLines("Day1.txt")
    .map { it.toInt() }

fun main() {
    first()
    second()
}

private fun first() {
    val result = input.asSequence()
        .mapNotNull { firstNumber -> multiplyIfMatches(firstNumber) }
        .first()
    println(result)
}

fun multiplyIfMatches(firstNumber: Int): Int? {
    val matchingSecondNumber = input.filter { it != firstNumber }
        .find { secondNumber -> secondNumber + firstNumber == 2020 }

    return if (matchingSecondNumber != null) {
        matchingSecondNumber * firstNumber;
    } else {
        null
    }
}

fun second() {
    val result = input.asSequence()
        .flatMap { firstNumber ->
            input.filter { it != firstNumber }
                .map { secondNumber -> NumberPair(firstNumber, secondNumber) }
                .filter { (_, _, sum) -> sum < 2020 }
                .asSequence()
        }
        .mapNotNull { (first, second, sum) ->
            val third = input.filter { it != first && it != second }
                .find { it + sum == 2020 }
            if (third != null) {
                first * second * third
            } else {
                null
            }
        }
        .first()

    println(result)
}

private data class NumberPair(val first: Int, val second: Int, val sum: Int = first + second)
