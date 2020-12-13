package de.cyril.aoc


fun main() {
    val regex = Regex("(?<min>\\d+)-(?<max>\\d+) (?<character>\\w): (?<pw>\\w+)")
    val mappedInput = InputReader.readLines("Day2.txt")
        .map {
            val (min, max, character, pw) = regex.matchEntire(it)!!.destructured
            PasswordInput(min.toInt(), max.toInt(), character[0], pw)
        }

    val validPasswords = mappedInput.count { passwordInput ->
        val characterCount = passwordInput.pw.count { passwordInput.character == it }
        characterCount >= passwordInput.min && characterCount <= passwordInput.max
    }
    println(validPasswords)
}


data class PasswordInput(val min: Int, val max: Int, val character: Char, val pw: String)