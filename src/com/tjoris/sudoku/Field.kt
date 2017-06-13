package com.tjoris.sudoku

import java.util.ArrayList

class Field(private val length: Int) {
    private val zones = Array(3 * length) { Zone() }
    private val cells = Array(length * length) { Cell() }

    fun addCellToZone(cellIndex: Int, zoneIndex: Int) {
        val cell = this.cells[cellIndex]
        val zone = this.zones[zoneIndex]
        zone.addCell(cell)
        cell.addZone(zone)
    }

    fun setValue(cellIndex: Int, value: Short) {
        this.cells[cellIndex].value = value
    }

    fun solve(conversion: Conversion): Array<Solution> {
        val solutions = ArrayList<Solution>()
        tryCell(conversion, 0, solutions)
        return solutions.toTypedArray()
    }

    private fun tryCell(conversion: Conversion, index: Int, solutions: MutableList<Solution>) {
        //print(conversion)
        if (index == this.cells.size) {
            val values = this.cells.map { cell -> cell.value }
            solutions.add(Solution(values))
        } else {
            val cell = this.cells[index]
            if (cell.value.toInt() != -1) {
                tryCell(conversion, index + 1, solutions)
            } else {
                for (i in 0 until this.length) {
                    if (cell.tryValue(i.toShort())) {
                        tryCell(conversion, index + 1, solutions)
                    }
                }
                cell.clearValue()
            }
        }
    }

    fun print(conversion: Conversion) {
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
            print(conversion.serialize(cell.value))
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
