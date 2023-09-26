package pr2.app.domain


import java.lang.Math.floor
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class Sudoku {
    private val SODOKU_NUMBER_OF_COLUMNS = 9
    private val SODOKU_NUMBER_OF_ROWS = 9
    private val SODOKU_INITIALIZE_NUMBER = 0
    private val SUDOKU_MAX_NUMBER = 10
    private val NUMBER_OF_SODUKONUMBERS = SODOKU_NUMBER_OF_COLUMNS * SODOKU_NUMBER_OF_ROWS;
    internal lateinit var BOARD_ARRAY: Array<Array<Int>>
    fun initializeFullBoard() {


        new@ do {

            this.BOARD_ARRAY = Array(SODOKU_NUMBER_OF_ROWS) { Array(SODOKU_NUMBER_OF_COLUMNS) { SODOKU_INITIALIZE_NUMBER } }
            val usedSudokuNumbers: ArrayList<Int> = arrayListOf()

            while (this.BOARD_ARRAY.flatten().any { sudokoNumber -> sudokoNumber == 0 }) {

                if (usedSudokuNumbers.count() == 9)  continue@new


                val usedCoordinates: ArrayList<Pair<Int, Int>> = arrayListOf()

                val sudokuNumber = randomSudokuNumber(usedSudokuNumbers)





                while (this.BOARD_ARRAY.flatten().count { sudokuNumberOnBoard -> sudokuNumberOnBoard == sudokuNumber } < 9) {
                    if(usedSudokuNumbers.count() ==8){
                        val a:Int
                    }
                    var coordinates : Pair <Int,Int>
                    var move : SodokuMove
                    do {
                        coordinates = randomCoordinates(usedCoordinates)
                        usedCoordinates.add(coordinates)
                        move = SodokuMove(coordinates.first, coordinates.second, sudokuNumber)
                        if (usedCoordinates.count() == 81) continue@new
                       } while (!checkIfMoveIsValid(move) || this.BOARD_ARRAY[coordinates.first] [coordinates.second] != 0)

                       this.makeMove(move)

                }
                    usedSudokuNumbers.add(sudokuNumber)
            }

        }while (this.BOARD_ARRAY.flatten().any { sudokoNumber -> sudokoNumber == 0 })

        print(showBoard())
    }


    fun createNewStartBoard(numberOfStartingNumbers: Int) {
        for (i in 0 until NUMBER_OF_SODUKONUMBERS - numberOfStartingNumbers) {
            var randRow: Int
            var randColumn: Int
            do {
                randRow = (0 until SODOKU_NUMBER_OF_ROWS).random()
                randColumn = (0 until SODOKU_NUMBER_OF_COLUMNS).random()
            } while (this.BOARD_ARRAY[randRow][randColumn] == 0)
            this.BOARD_ARRAY[randRow][randColumn] = 0
        }
    }

    private fun randomCoordinates (usedCoordinates: ArrayList<Pair<Int, Int>>) : Pair<Int, Int>{
        var rowIndex :Int
        var columnIndex :Int

        do {
            rowIndex = (0 until 9).random()
            columnIndex = (0 until 9).random()
        } while (usedCoordinates.contains(Pair(rowIndex, columnIndex)) )
    return Pair(rowIndex,columnIndex)
    }
    private fun randomSudokuNumber(lastNumber: ArrayList<Int>): Int {
        var sudokuNumber: Int
        do {
            sudokuNumber = (1..9).random()
        } while (lastNumber.contains(sudokuNumber))
        return sudokuNumber
    }

    private fun checkIfMoveIsValid(move: SodokuMove): Boolean {

        return (notInSquare(move) && notInColumn(move) && notInRow(
            move
        ))
    }

    private fun notInColumn(move: SodokuMove): Boolean {
        for (rowIndex in 0 until SODOKU_NUMBER_OF_ROWS) {
            if (this.BOARD_ARRAY[rowIndex][move.columnIndex] == move.number) {
                return false
            }
        }
        return true
    }

    private fun notInSquare(move: SodokuMove): Boolean {
        var startColumnIndex :Int
        var startRowIndex : Int
        val QUANTITY_OF_ROWS_PER_SQUARE = 3
        startColumnIndex = floor(move.columnIndex / 3.0).roundToInt() * 3
        startRowIndex = floor(move.rowIndex / 3.0).roundToInt() * 3

        for (row in 0 until QUANTITY_OF_ROWS_PER_SQUARE) {
            for (column in 0 until QUANTITY_OF_ROWS_PER_SQUARE) {
                if (this.BOARD_ARRAY[row + startRowIndex][column + startColumnIndex] == move.number) {
                    return false
                }
            }
        }

        return true
    }

    private fun notInRow(move: SodokuMove): Boolean {
        return !this.BOARD_ARRAY[move.rowIndex].contains(move.number)

    }

    private fun getRandomMove(): SodokuMove {
        val randColumn = (0 until SODOKU_NUMBER_OF_COLUMNS).random()
        val randRow = (0 until SODOKU_NUMBER_OF_COLUMNS).random()
        val randSodokuNumber = (0 until SUDOKU_MAX_NUMBER).random()
        return SodokuMove(randRow, randColumn, randSodokuNumber)

    }

    fun makeMove(move: SodokuMove) {
        this.BOARD_ARRAY[move.rowIndex][move.columnIndex] = move.number
    }

    fun checkWin(): Boolean {


        for (a in this.BOARD_ARRAY[0].indices) {
            var numberListRow = ArrayList<Int>()
            var numberListColumn = ArrayList<Int>()


            for (b in this.BOARD_ARRAY[0].indices) {
                if (!numberListColumn.contains(this.BOARD_ARRAY[b][a]))
                    numberListColumn.add(this.BOARD_ARRAY[a][b])
                else
                    return false
                if (!numberListRow.contains(this.BOARD_ARRAY[a][b]))
                    numberListRow.add(this.BOARD_ARRAY[a][b])
                else
                    return false
            }

        }
        return true
    }

    fun showBoard() {
        for (i in 0..8) {
            println()
            // evt. ohne externe Iteration implementieren
            this.BOARD_ARRAY[i].forEach { a -> print(" " + a.toString()) }

        }
    }

    fun suggestMove(): SodokuMove {
        var suggestedMove: SodokuMove
        do {
            suggestedMove = getRandomMove()
        } while (!checkIfMoveIsValid(suggestedMove))

        return suggestedMove;
    }

}

