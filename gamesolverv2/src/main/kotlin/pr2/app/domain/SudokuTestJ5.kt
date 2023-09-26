package pr2.app.domain

import org.junit.jupiter.api.Assertions.*

internal class SudokuTestJ5 {

    @org.junit.jupiter.api.Test
    fun initializeFullBoard() {
        var testSudoku = Sudoku()
        testSudoku.initializeFullBoard()
        val countZeros = testSudoku.BOARD_ARRAY.flatten().count { number -> number ==0 }
        assertEquals(0, countZeros)
    }

    @org.junit.jupiter.api.Test
    fun createNewStartBoard() {
    }

    @org.junit.jupiter.api.Test
    fun makeMove() {
    }

    @org.junit.jupiter.api.Test
    fun checkWin() {
    }

    @org.junit.jupiter.api.Test
    fun showBoard() {
    }

    @org.junit.jupiter.api.Test
    fun suggestMove() {
    }
}