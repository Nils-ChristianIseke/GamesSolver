package pr2.app.gui.View

import javafx.scene.control.TextField
import pr2.app.`interface`.SudokuApplication
import pr2.app.gui.App.Styles
import tornadofx.*
import java.lang.Exception

class SudokuView : View("Sudoku Solver") {

    private var eingabeFelder: HashMap<Pair<Int,Int>,TextField> = HashMap()
    //private var gameApp: SudokuApplication = SudokuApplication("Sudoku-Name")

    init {
        //gameApp.initializeBoard()
        //gameApp.
        /*
        val ausgabe : Array<Array<Int>> = gameApp.getBoardMap()
        setAusgabe(ausgabe)

         */
    }

    override val root = gridpane {
        for (i in 0..8) {
            row {
                for (j in 0..8) {
                    val index = Pair(i,j)
                    eingabeFelder[index] = textfield {
                        addClass(Styles.eingabeSudoku)
                        if (j == 2 || j == 5)
                            addClass(Styles.eingabeSudokuBorderRight)
                        if (i == 2 || i == 5)
                            addClass(Styles.eingabeSudokuBorderBottom)

                        if (this.hasClass(Styles.eingabeSudokuBorderBottom) && this.hasClass(Styles.eingabeSudokuBorderRight))
                            this.addClass(Styles.eingabeSudokuBorderBottomRight)

                        filterInput { e -> (e.controlNewText.length <= 1) && e.controlNewText.contains(Regex("[123456789]")) }
                    }
                }
            }
        }
    }

    fun setAusgabe(input: Array<Array<Int>>): Unit {
        /*
        for (i in 0..8) {
            for (j in 0..8) {
                if (eingabeFelder[i][j]!!.text != input[i][j]) {
                    eingabeFelder[i][j]!!.text  = input[i][j]
                    eingabeFelder[i][j]!!.isEditable = false;
                }
            }
        }

         */
    }
}

