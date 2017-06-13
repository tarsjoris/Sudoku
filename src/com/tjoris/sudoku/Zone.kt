package com.tjoris.sudoku

import java.util.ArrayList

class Zone {
    private val cells = ArrayList<Cell>()

    fun addCell(cell: Cell) = this.cells.add(cell)

    fun isValid(value: Short) = this.cells.none { it.value == value }
}
