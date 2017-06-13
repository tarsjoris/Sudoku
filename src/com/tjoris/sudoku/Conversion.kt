package com.tjoris.sudoku

class Conversion(private val startValue: Short, private val maxValue: Int) {

    fun parse(ch: Char): Short {
        var value: Short
        if (ch in '0'..'9') {
            value = (ch - '0').toShort()
        } else if (ch in 'A'..'Z') {
            value = (ch - 'A' + 10).toShort()
        } else {
            throw IllegalArgumentException("Character '$ch' not supported.")
        }
        value = (value - this.startValue).toShort()
        if (value < 0) {
            throw IllegalArgumentException("Character '$ch' is too small.")
        }
        if (value >= this.maxValue) {
            throw IllegalArgumentException("Character '$ch' is too large.")
        }
        return value
    }

    fun serialize(value: Short): Char {
        if (value < 0) {
            return ' '
        }
        val ch = value + this.startValue
        return if (ch < 10) '0' + ch else 'A' + ch - 1
    }
}
