package com.tjoris.sudoku

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

fun main(args: Array<String>) {
    try {
        if (args.isEmpty()) {
            println("Usage: configuration-file")
            return
        }
        val file = File(args[0])
        println("Reading file: ${file.absolutePath}")
        val field = BufferedReader(InputStreamReader(FileInputStream(file))).use(::readField)

        val solutions = field.solve()

        if (solutions.isEmpty()) {
            println("No solutions")
        } else {
            solutions.forEach { it.print() }
        }
    } catch (e: Exception) {
        e.printStackTrace(System.out)
    }
}

fun readField(reader: BufferedReader): FieldWithZones {
    val size = Integer.parseInt(reader.readLine())
    val length = size * size
    val startValue = Integer.parseInt(reader.readLine())
    val conversion = Conversion(startValue.toShort(), length)

    val field = FieldWithZones(length, conversion)
    var line = reader.readLine()
    if (line == "normal") {
        // normal 2-d sudoku's
        for (i in 0 until length) {
            for (j in 0 until length) {
                field.addCellToZone(i * length + j, i)
                field.addCellToZone(i + j * length, length + i)
                field.addCellToZone(i / size * length * size + i % size * size + j / size * length + j % size, 2 * length + i)
            }
        }
    } else {
        // special dimension sudoku's (e.g. 3d)
        for (zoneIndex in 0 until 3 * length) {
            line = reader.readLine()
            for (index in 0 until length) {
                field.addCellToZone(conversion.parse(line[index]).toInt(), zoneIndex)
            }
        }
    }
    for (i in 0 until length) {
        line = reader.readLine()
        for (j in 0 until length) {
            val ch = line[j]
            if (ch != '.') {
                field.setValue(i * length + j, conversion.parse(ch))
            }
        }
    }
    return field
}
