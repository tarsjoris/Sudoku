package com.tjoris.sudoku

class Solution(private val values: List<Short>) {

    fun print(conversion: Conversion) {
        val length = Math.sqrt(values.size.toDouble()).toInt()
        val size = Math.sqrt(length.toDouble()).toInt()
        printSeparator(size)
        values.forEachIndexed { i, value ->
            if (i % size == 0) {
                print('|')
            }
            if (i > 0 && i % length == 0) {
                println()
                if (i % (size * length) == 0) {
                    printSeparator(size)
                }
                print('|')
            }
            print(conversion.serialize(value))
        }
        println('|')
        printSeparator(size)
    }

    private fun printSeparator(size: Int) {
        for (i in 0..size - 1) {
            print('+')
            for (j in 0..size - 1) {
                print('-')
            }
        }
        println('+')
    }
}
