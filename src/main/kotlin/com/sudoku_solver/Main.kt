@file:JvmName("Main")
package com.sudoku_solver
import java.io.File

fun main() {
    val input: Map<Coordinate, Int> = readFile("sudoku")
    val solution: Map<Coordinate, Int> = readFile("solved")

    var result = CheckOutcome.Ok
    for(i in 0 until 9) {
        val rowCheckOutcome = checkLine(input, solution, rowCoordinateGenerator(i))
        if(rowCheckOutcome != CheckOutcome.Ok) {
            result = rowCheckOutcome
            break
        }
        val columnCheckOutcome = checkLine(input, solution, colCoordinateGenerator(i))
        if(columnCheckOutcome != CheckOutcome.Ok) {
            result = columnCheckOutcome
            break
        }
        val quadrantCheckOutcome = checkLine(input, solution, quadCoordinateGenerator(i))
        if(quadrantCheckOutcome != CheckOutcome.Ok) {
            result = quadrantCheckOutcome
            break
        }
    }
    println(result)
}

fun checkLine(input: Map<Coordinate, Int>, solution: Map<Coordinate, Int>, coordinateGenerator: Generator): CheckOutcome {
    for (i in 0 until 8) {
        val checkedCoordinate = coordinateGenerator(i)
        val checkedValue = input[checkedCoordinate] ?: solution[checkedCoordinate] ?: return CheckOutcome.Incomplete
        for (j in i + 1 until 9) {
            val internalCoordinate = coordinateGenerator(j)
            val internalValue = input[internalCoordinate] ?: solution[internalCoordinate] ?: return CheckOutcome.Incomplete
            if(checkedValue == internalValue) {
                return CheckOutcome.Failed
            }
        }
    }
    return CheckOutcome.Ok
}

fun readFile(name: String) : Map<Coordinate, Int> =
    File(name)
        .readLines()
        .withIndex()
        .flatMap { indexedValue ->
            val xCoordinate = indexedValue.index
            indexedValue.value.toCharArray().withIndex()
                .filter { indexedChar -> indexedChar.value != '.' }
                .map { indexedChar ->
                    val yCoordinate = indexedChar.index
                    val coordinate = Coordinate(xCoordinate, yCoordinate)
                    val number = Character.getNumericValue(indexedChar.value)
                    coordinate to number
                }
        }.toMap()
