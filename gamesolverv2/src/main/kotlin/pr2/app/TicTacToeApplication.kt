package pr2.app

import pr2.app.domain.*

class TicTacToeApplication(val name: String, val firstPlayerSymbol: TicTacToeSymbol) {
    private val ticTacToe = TicTacToe(
        firstPlayerSymbol = firstPlayerSymbol,
        secondPlayerSymbol = if (firstPlayerSymbol == TicTacToeSymbol.CIRCLE) TicTacToeSymbol.CROSS else TicTacToeSymbol.CIRCLE
    )

    fun makeMove(zug: TicTacToeMove) = ticTacToe.makeMove(zug)
    fun checkMove(zug: TicTacToeMove) = ticTacToe.checkMove(zug)
    fun checkWin() = ticTacToe.checkWin()
    fun suggestMove(ki: KI) = ticTacToe.suggestMove(ki)
    fun whosTurn() = ticTacToe.whosTurn()
    fun getSymbol(x: Int, y: Int) = ticTacToe.getSymbol(x, y)
}

class SudokuApplication(val name: String) {
    private val sudoku = Sodoku()
    fun makeMove(zug: SodokuMove) = sudoku.makeMove(zug)

    // fun checkMove(zug: SodokuMove) = sodoku.checkMove(zug)
    fun checkWin() = sudoku.checkWin()
    // fun suggestMove(ki: KI) = sodoku.suggestMove(ki)

    fun initializeBoard() = sudoku.initializeFullBoard()

    // TODO generate playable Sudoku
    /*
    fun getBoardMap() : Array<Array<Int>> = sudoku.getBoard()

     */

}