package com.tjoris.sudoku

import java.util.ArrayList

class Cell {
    private val zones = ArrayList<Zone>()
    var value: Short = -1

    fun addZone(zone: Zone) = this.zones.add(zone)

    fun tryValue(value: Short): Boolean {
        if (!this.zones.all { it.isValid(value) }) {
            return false
        }
        this.value = value
        return true
    }

    fun clearValue() {
        this.value = -1
    }
}
