package pr2.app.domain

class TicTacToe (
    private val  boardArray : Array<Array<TicTacToeField>> = Array(3){ Array(3){ TicTacToeField(TicTacToeSymbol.EMPTY) }},
    private var firstPlayerTurns : Boolean = true,
    private val firstPlayerSymbol: TicTacToeSymbol = TicTacToeSymbol.CIRCLE,
    private val secondPlayerSymbol: TicTacToeSymbol = TicTacToeSymbol.CROSS
){
    //Noah Ergänzungen
    fun whosTurn() : TicTacToeSymbol{
        return if(firstPlayerTurns) firstPlayerSymbol else secondPlayerSymbol
    }
    fun getSymbol(x:Int, y:Int) : TicTacToeSymbol {
        if((x in 0..2) && (y in 0..2)) // (((x >= 0) && (x <= 2)) && ((y >= 0) && (y <= 2)))
            return boardArray[x][y].symbol;

        throw IllegalArgumentException("Out of Bounds!")
    }
    //haoN


    fun makeMove(zug : TicTacToeMove){
        if(checkMove(zug))
        this.boardArray[zug.coordinateX][zug.coordinateY].symbol = if (firstPlayerTurns) firstPlayerSymbol else secondPlayerSymbol
        firstPlayerTurns = !firstPlayerTurns
    }

    fun checkMove(zug : TicTacToeMove): Boolean = this.boardArray[zug.coordinateX][zug.coordinateY].symbol == TicTacToeSymbol.EMPTY

    fun checkWin() : Boolean {
        for (a in this.boardArray.indices) {
            if (this.boardArray[a][0].symbol != TicTacToeSymbol.EMPTY && this.boardArray[a][0] == this.boardArray[a][1]
                && this.boardArray[a][1] == this.boardArray[a][2]
            ){
                return true
            }
                for (b in this.boardArray[0].indices ) {
                    if (this.boardArray[0][b].symbol != TicTacToeSymbol.EMPTY && this.boardArray[0][b] == this.boardArray[1][b]
                        && this.boardArray[0][b] == this.boardArray[2][b]
                    ){
                        return true
                    }

                }
        }
        return false
        }

    fun suggestMove(ki: KI)
    {

    }






}