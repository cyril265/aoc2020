package de.cyril.aoc

class InputReader {

    companion object {

        fun readLines(inputFile: String): List<String> {
            return ClassLoader.getSystemResource(inputFile).readText()
                .split("\r\n")
        }

    }

}