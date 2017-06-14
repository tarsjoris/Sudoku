package com.tjoris.sudoku

import java.util.ArrayList

private const val kEMPTY : Short = -1

class Cell {
    var value: Short = kEMPTY

    private val zones = ArrayList<Zone>()


    fun addZone(zone: Zone) = this.zones.add(zone)

    fun tryValue(value: Short): Boolean {
        if (this.zones.all { it.isValid(value) }) {
            this.value = value
            return true
        } else {
            return false
        }
    }

    fun clearValue() {
        this.value = kEMPTY
    }

    fun isEmpty() = this.value == kEMPTY
}
