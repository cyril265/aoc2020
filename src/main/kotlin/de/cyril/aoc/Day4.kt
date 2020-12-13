package de.cyril.aoc


fun readLines(): List<String> {
    return ClassLoader.getSystemResource("day4.txt")
        .readText()
        .split("\r\n\r\n")
        .map { it.replace("\r\n", " ") }
}


fun main() {
    part1()
    part2()
}


private val fields = arrayOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

private fun part1() {
    val passports = readLines()
    val count = passports.count { passport ->
        fields.all { hasField(passport, it) }
    }
    println(count)
}


private val validatedFields = arrayOf(
    Field("byr") { value -> checkBetween(value, 1920, 2002) },
    Field("iyr") { value -> checkBetween(value, 2010, 2020) },
    Field("eyr") { value -> checkBetween(value, 2020, 2030) },
    Field("hgt") { value ->
        when {
            value.endsWith("cm") -> {
                checkBetween(value.substringBefore("cm"), 150, 193)
            }
            value.endsWith("in") -> {
                checkBetween(value.substringBefore("in"), 59, 76)
            }
            else -> {
                false
            }
        }
    },
    Field("hcl") { value ->
        val hex = value.substringAfter("#")
        value.startsWith('#') && hex.length == 6 && isHex(hex)
    },
    Field("ecl") { value -> value in arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
    Field("pid") { value -> value.count { it.isDigit() } == 9 }
)

private fun isHex(value: String) = value.all { it.isDigit() || it in arrayOf('a', 'b', 'c', 'd', 'e', 'f') }
private fun checkBetween(value: String, start: Int, end: Int) = value.toIntOrNull()?.let { it in start..end } == true

private fun part2() {
    val passports = readLines()
    val count = passports
        .count { passport ->
            validatedFields.all { field ->
                val value = getFieldValue(passport, field.name)
                field.validate(value)
            }
        }
    println(count)
}

private fun hasField(passport: String, name: String) = passport.contains("$name:")
private fun getFieldValue(passport: String, name: String) = passport.substringAfter("$name:").substringBefore(" ")

private class Field(val name: String, val validate: (String) -> Boolean)
