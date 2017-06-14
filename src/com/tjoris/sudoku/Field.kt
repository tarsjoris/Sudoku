package com.tjoris.sudoku

open class Field(private val length: Int, private val conversion : Conversion) {
    protected val cells = Array(length * length) { Cell() }

    constructor(field : Field) : this(field.length, field.conversion){
        this.cells.forEachIndexed { i, cell ->
            cell.value = field.cells[i].value
        }
    }

    fun print() {
        val size = Math.sqrt(this.length.toDouble()).toInt()
        printSeparator(size)
        this.cells.forEachIndexed { i, cell ->
            if (i % size == 0) {
                print('|')
            }
            if (i > 0 && i % this.length == 0) {
                println()
                if (i % (size * this.length) == 0) {
                    printSeparator(size)
                }
                print('|')
            }
            print(this.conversion.serialize(cell))
        }
        println('|')
        printSeparator(size)
    }

    private fun printSeparator(size: Int) {
        for (i in 0 until size) {
            print('+')
            for (j in 0 until size) {
                print('-')
            }
        }
        println('+')
    }
}
