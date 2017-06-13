package com.tjoris.sudoku

import java.io.IOException

class Conversion(private val startValue: Short, private val maxValue: Int) {

    fun parse(ch: Char): Short {
        var value: Short = 0
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
        var ch = value + this.startValue
        if (ch < 10) {
            return ('0' + ch).toChar()
        } else {
            return ('A' + ch - 10).toChar()
        }
    }
}
