package com.tjoris.sudoku

import java.util.ArrayList

class FieldWithZones(private val length: Int, conversion : Conversion) : Field(length, conversion) {
    private val zones = Array(3 * length) { Zone() }

    fun addCellToZone(cellIndex: Int, zoneIndex: Int) {
        val cell = this.cells[cellIndex]
        val zone = this.zones[zoneIndex]
        zone.addCell(cell)
        cell.addZone(zone)
    }

    fun setValue(cellIndex: Int, value: Short) {
        this.cells[cellIndex].value = value
    }

    fun solve(): Array<Field> {
        val solutions = ArrayList<Field>()
        tryCell(0, solutions)
        return solutions.toTypedArray()
    }

    private fun tryCell(index: Int, solutions: MutableList<Field>) {
        //print()
        if (index == this.cells.size) {
            solutions.add(Field(this))
        } else {
            val cell = this.cells[index]
            if (cell.isEmpty()) {
                for (i in 0 until this.length) {
                    if (cell.tryValue(i.toShort())) {
                        tryCell(index + 1, solutions)
                    }
                }
                cell.clearValue()
            } else {
                tryCell(index + 1, solutions)
            }
        }
    }
}
