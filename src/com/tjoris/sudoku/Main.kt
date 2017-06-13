package com.tjoris.sudoku

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

fun main(args: Array<String>) {
    try {
        if (args.size == 0) {
            println("Usage: configuration-file")
            return
        }
        val file = File(args[0])
        println("Reading file: " + file.absolutePath)
        BufferedReader(InputStreamReader(FileInputStream(file))).use { reader ->
            val size = Integer.parseInt(reader.readLine())
            val length = size * size
            val startValue = Integer.parseInt(reader.readLine())
            val conversion = Conversion(startValue.toShort(), length)
            val field = readField(size, conversion, reader)
            val solutions = field.solve(conversion)
            if (solutions.size == 0) {
                println("No solutions")
            } else {
                solutions.forEach { it.print(conversion) }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace(System.out)
    }

}

fun readField(size: Int, conversion: Conversion, reader: BufferedReader): Field {
    val length = size * size
    val field = Field(length)
    var line = reader.readLine()
    if (line == "normal") {
        // normal 2-d sudoku's
        for (i in 0..length - 1) {
            for (j in 0..length - 1) {
                field.addCellToZone(i * length + j, i)
                field.addCellToZone(i + j * length, length + i)
                field.addCellToZone(i / size * length * size + i % size * size + j / size * length + j % size, 2 * length + i)
            }
        }
    } else {
        // special dimension sudoku's (e.g. 4d)
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
