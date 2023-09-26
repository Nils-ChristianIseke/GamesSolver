package pr2.app.`interface`

import pr2.app.domain.*

class TicTacToeApplication (val name: String, val firstPlayerSymbol: TicTacToeSymbol) {
    private val ticTacToe = TicTacToe(
        firstPlayerSymbol = firstPlayerSymbol,
        secondPlayerSymbol = if (firstPlayerSymbol == TicTacToeSymbol.CIRCLE) TicTacToeSymbol.CROSS else TicTacToeSymbol.CIRCLE)
    fun makeMove(zug: TicTacToeMove) = ticTacToe.makeMove(zug)
    fun checkMove(zug: TicTacToeMove) = ticTacToe.checkMove(zug)
    fun checkWin() = ticTacToe.checkWin()
    fun suggestMove(ki: KI) = ticTacToe.suggestMove(ki)
    fun whosTurn() = ticTacToe.whosTurn()
    fun getSymbol(x:Int, y:Int) = ticTacToe.getSymbol(x , y)
}
class SodokuApplication(val name: String,val  numberOfStartingNumbers :Int) {
    private val sodoku = Sudoku()
    fun makeMove(zug: SodokuMove) =sodoku.makeMove(zug)
   // fun checkMove(zug: SodokuMove) = sodoku.checkMove(zug)
    fun checkWin() = sodoku.checkWin()
    // fun suggestMove(ki: KI) = sodoku.suggestMove(ki)

    fun initializeBoard() = sodoku.createNewStartBoard(numberOfStartingNumbers)

}