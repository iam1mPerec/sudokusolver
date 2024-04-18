package com.sudoku_solver

typealias Generator = (Int) -> Coordinate

fun rowCoordinateGenerator(rowNumber: Int): Generator = { index -> Coordinate(x = rowNumber, y = index) }
fun colCoordinateGenerator(colNumber: Int): Generator = { index -> Coordinate(x = index, y = colNumber) }
fun quadCoordinateGenerator(quadNumber: Int): Generator = { index -> Coordinate(x = index/3+3*(quadNumber/3), y = index%3+3*(quadNumber%3)) }
