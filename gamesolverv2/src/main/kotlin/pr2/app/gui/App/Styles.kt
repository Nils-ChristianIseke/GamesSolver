package pr2.app.gui.App

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*
import java.awt.TextField

class Styles : Stylesheet() {
    companion object {
        val eingabeTicTacToe by cssclass()
        val changedValue by cssclass()
        val solveButton by cssclass()
        val mainMenuButton by cssclass()
        val turnSelector by cssclass()
        val finalized by cssclass()
        val finalizedTicTacToe by cssclass()
        val eingabeSudoku by cssclass()
        val eingabeSudokuBorderRight by cssclass()
        val eingabeSudokuBorderBottom by cssclass()
        val eingabeSudokuBorderBottomRight by cssclass()
    }

    init {
        solveButton {
            fontFamily = "Comic Sans MS"
            fontSize = 20.px
        }
        textField and eingabeTicTacToe {
            backgroundColor += Color.ALICEBLUE
            fontWeight = FontWeight.BOLD
            borderRadius += box(0.px)
            textAlignment = TextAlignment.CENTER
            minWidth = 30.px
            maxWidth = 30.px
            borderColor += box(Color.BLACK)
        }

        changedValue {
            baseColor = Color.LIGHTSEAGREEN
            fontWeight = FontWeight.BOLD
            borderRadius += box(0.px)
            textAlignment = TextAlignment.CENTER
            minWidth = 30.px
            maxWidth = 30.px
            borderColor += box(Color.BLACK)
        }

        mainMenuButton {
            minWidth = 100.px
        }

        turnSelector{
            fontSize = 14.px
        }

        finalized {
            baseColor = Color.LIGHTSLATEGREY


        }

        finalizedTicTacToe{
            baseColor = Color.LIGHTSLATEGREY
            fontWeight = FontWeight.BOLD
            borderRadius += box(0.px)
            textAlignment = TextAlignment.CENTER
            minWidth = 30.px
            maxWidth = 30.px
            borderColor += box(Color.BLACK)
        }

        eingabeSudoku{
            backgroundColor += Color.ANTIQUEWHITE
            fontWeight = FontWeight.BOLD
            borderRadius += box(0.px)
            textAlignment = TextAlignment.CENTER
            minWidth = 30.px
            maxWidth = 30.px
            borderColor += box(Color.BLACK)
        }

        eingabeSudokuBorderRight{
            borderWidth = MultiValue(arrayOf(CssBox(1.px,4.px,1.px,1.px)))
        }
        eingabeSudokuBorderBottom{
            borderWidth = MultiValue(arrayOf(CssBox(1.px,1.px,4.px,1.px)))
        }
        eingabeSudokuBorderBottomRight{
            borderWidth = MultiValue(arrayOf(CssBox(1.px,4.px,4.px,1.px)))
        }
    }
}