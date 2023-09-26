package pr2.app.gui.View

import javafx.geometry.Pos
import tornadofx.*

class MenuView : View("Main Menu") {
    override val root = vbox {
        paddingAll = 10
        alignment = Pos.TOP_CENTER
        button("Tic-Tac-Toe-Engine") {
            alignment = Pos.CENTER
            action {
                find<TicTacToeView>().openModal()
            }
        }
        button("Sudoku-Solver") {
            alignment = Pos.CENTER
            action {
                find<SudokuView>().openModal()
            }
        }
    }
}

/*
class SudokuView : View("Sudoku-Solver") {
    override val root = vbox {
        button("placeholder")
    }
}

 */




