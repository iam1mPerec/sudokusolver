package com.sudoku_solver

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GeneratorsKtTest {

    @Test
    fun `row generator should return coordinates in a single row`() {
        val coordinate = rowCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(0,1), coordinate)
    }

    @Test
    fun `column generator should return coordinates in a single column`() {
        val coordinate = colCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(1,0), coordinate)
    }

    @Test
    fun `quad generator should return coordinates in a quad`() {
        val coordinate = quadCoordinateGenerator(1).invoke(0)
        assertEquals(Coordinate(0,3), coordinate)
    }
}