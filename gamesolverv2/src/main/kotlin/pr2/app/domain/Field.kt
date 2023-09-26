package pr2.app.domain

import java.lang.IllegalStateException

enum class TicTacToeSymbol {
    EMPTY, CROSS, CIRCLE;

    fun toChar():Char?{
        if(this.equals(EMPTY))
            return null
        if(this.equals(CROSS))
            return 'x'
        if(this.equals(CIRCLE))
            return 'o'

        throw IllegalStateException()
    }
}

class TicTacToeField (var symbol : TicTacToeSymbol)


