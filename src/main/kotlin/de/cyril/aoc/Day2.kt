package de.cyril.aoc


fun main() {
    val regex = Regex("(?<min>\\d+)-(?<max>\\d+) (?<character>\\w): (?<pw>\\w+)")
    val mappedInput = InputReader.readLines("day2.txt")
        .map {
            val (min, max, character, pw) = regex.matchEntire(it)!!.destructured
            PasswordInput(min.toInt(), max.toInt(), character[0], pw)
        }

    part1(mappedInput)
    part2(mappedInput)
}

private fun part1(mappedInput: List<PasswordInput>) {
    val validPasswords = mappedInput.count { passwordInput ->
        val characterCount = passwordInput.pw.count { passwordInput.character == it }
        characterCount >= passwordInput.min && characterCount <= passwordInput.max
    }
    println(validPasswords)
}

private fun part2(mappedInput: List<PasswordInput>) {
    val validPasswords = mappedInput.count { passwordInput ->
        val firstChar = passwordInput.pw.elementAtOrNull(passwordInput.min - 1)
        val secondChar = passwordInput.pw.elementAtOrNull(passwordInput.max - 1)

        (firstChar == passwordInput.character) xor (secondChar == passwordInput.character)
    }
    println(validPasswords)
}


data class PasswordInput(val min: Int, val max: Int, val character: Char, val pw: String)