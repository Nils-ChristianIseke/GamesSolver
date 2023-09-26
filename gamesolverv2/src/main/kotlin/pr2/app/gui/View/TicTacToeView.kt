package pr2.app.gui.View

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import pr2.app.`interface`.TicTacToeApplication
import pr2.app.domain.TicTacToeMove
import pr2.app.domain.TicTacToeSymbol
import pr2.app.gui.App.Styles
import tornadofx.*

class TicTacToeView : View("My View") {
    private val eingabeFelder = hashMapOf<Pair<Int, Int>, TextField>()
    private val turnSelector = ToggleGroup()

    private lateinit var gameApp: TicTacToeApplication
    private var gameStarted: Boolean = false
    private lateinit var toggleX: ToggleButton
    private lateinit var toggleO: ToggleButton

    override val root = vbox {
        alignment = Pos.CENTER
        paddingAll = 20
        label("Wer ist am Zug?")
        hbox {
            paddingAll = 10
            alignment = Pos.CENTER
            toggleX = togglebutton("x", turnSelector, value = TicTacToeSymbol.CROSS) {
                addClass(Styles.turnSelector)
                if (gameStarted)
                    if (gameApp.whosTurn() == TicTacToeSymbol.CROSS)
                        turnSelector.selectToggle(this)

            }
            toggleO = togglebutton("o", turnSelector, value = TicTacToeSymbol.CIRCLE) {
                addClass(Styles.turnSelector)
                if (gameStarted)
                    if (gameApp.whosTurn() == TicTacToeSymbol.CIRCLE)
                        turnSelector.selectToggle(this)
            }
        }
        vbox {
            paddingAll = 10
            alignment = Pos.TOP_CENTER
            gridpane {
                alignment = Pos.CENTER
                for (i in 0..2) {
                    row {
                        for (j in 0..2) {
                            eingabeFelder[Pair(i, j)] = textfield {
                                addClass(Styles.eingabeTicTacToe)
                                filterInput { e -> (e.controlNewText.length <= 1) && e.controlNewText.contains(Regex("[ox]")) } //max. 1 Zeichen im Feld; nur o und x
                            }
                        }
                    }
                }
            }
        }
        hbox {
            alignment = Pos.CENTER
            button("Make move") {
                addClass(Styles.solveButton)
                action {
                    if (!gameStarted) {
                        val toggleProperty = turnSelector.selectedToggle.properties.get("tornadofx.toggleGroupValue")
                        val selectedStarter : TicTacToeSymbol = toggleProperty as TicTacToeSymbol // casting
                        gameApp = TicTacToeApplication("Name",selectedStarter)
                    }
                    gameStarted = true
                    //val fields = eingabeFelder.stream().map(TextField::getText).map(String::toCharArray).toList()
                    val fields = eingabeFelder.toList()
                    //val fieldArray = fields.chunked(3)
                    //var i = 0

                    if (gameApp.whosTurn() == TicTacToeSymbol.CIRCLE) {
                        toggleX.isDisable = false
                        toggleO.isDisable = true
                    } else {
                        toggleX.isDisable = true
                        toggleO.isDisable = false
                    }

                    for (i in 0..2) {
                        for (j in 0..2) {
                            if (eingabeFelder.get(Pair(i, j))?.text?.length != 0) {
                                val fieldChar: Char = eingabeFelder.get(Pair(i, j))!!.text.last()
                                val fieldSymbol: TicTacToeSymbol = charToSymbol(fieldChar)

                                if (fieldSymbol != gameApp.getSymbol(i, j)) {
                                    val move = TicTacToeMove(i, j)
                                    if (gameApp.checkMove(move))
                                        gameApp.makeMove(move)
                                }
                            }
                        }
                    }
                    /*
                fieldArray.iterator().forEachRemaining { e ->
                    run {
                        for (j in 0..2) {
                            if (e[i][j] != gameApp.game.boardArray[i][j].figure?.symbol) {
                                gameApp.makeMove(intArrayOf(i, j))
                            }
                            i++
                        }
                    }
                }

                 */
                    //solveTicTacToe
                    //val calculatedFields = gameApp.board.boardArray.flatMap { it.toList() }
                    val fieldMap = hashMapOf<Pair<Int, Int>, TicTacToeSymbol>()
                    var k = 0
                    for (i in 0..2) {
                        for (j in 0..2) {
                            fieldMap[Pair(i, j)] = gameApp.getSymbol(i, j)
                            k++
                        }
                    }
                    setAusgabe(fieldMap)
                    /*
                for (i in 0..8) {
                    if (eingabeFelder[i].text != calculatedField.[i]) {
                        if (calculatedField[i] != null) {
                            eingabeFelder[i].removeClass(Styles.eingabeTicTacToe)
                            eingabeFelder[i].addClass(Styles.changedValue)
                        }
                    } else {
                        eingabeFelder[i].removeClass(Styles.changedValue)
                        eingabeFelder[i].addClass(Styles.eingabeTicTacToe)
                    }
                    eingabeFelder[i].text = calculatedField[i]
                }
                */
                    if (gameApp.checkWin()) {
                        finalScreen()
                    }
                }
            }
            button("Suggest move"){
                addClass(Styles.solveButton)
                action {

                }
            }
        }

    }


private fun finalScreen() {
    for (feld in eingabeFelder.values) {
        feld.removeClass(Styles.eingabeTicTacToe)
        feld.addClass(Styles.finalizedTicTacToe)
        feld.isEditable = false
    }
    if (gameApp.whosTurn() == TicTacToeSymbol.CROSS)
        turnSelector.selectToggle(toggleX)
    else
        turnSelector.selectToggle(toggleO)

    root.addClass(Styles.finalized)

}

private fun setAusgabe(calculatedFields: HashMap<Pair<Int, Int>, TicTacToeSymbol>) {
    for (i in 0..2) {
        for (j in 0..2) {
            val index = Pair(i, j)
            if (eingabeFelder[index]?.text?.isNotEmpty() == true) {
                if (eingabeFelder[index]?.text?.get(0) != symbolToChar(calculatedFields[index])) {
                    eingabeFelder[index]?.removeClass(Styles.eingabeTicTacToe)
                    eingabeFelder[index]?.addClass(Styles.changedValue)
                } else if (eingabeFelder[index]?.hasClass(Styles.changedValue) == true) {
                    eingabeFelder[index]!!.removeClass(Styles.changedValue)
                    eingabeFelder[index]!!.addClass(Styles.eingabeTicTacToe)
                }
            }
            eingabeFelder[Pair(i, j)]?.text = symbolToChar(calculatedFields[index]).toString()
            eingabeFelder[index]?.isEditable = calculatedFields[index] == TicTacToeSymbol.EMPTY
        }
    }
    turnSelector.selectedValueProperty<TicTacToeSymbol>().set(gameApp.whosTurn())
}

private fun charToSymbol(c: Char?): TicTacToeSymbol {
    if (c == 'x')
        return TicTacToeSymbol.CROSS
    if (c == 'o')
        return TicTacToeSymbol.CIRCLE
    if (c == null)
        return TicTacToeSymbol.EMPTY

    throw IllegalArgumentException("Not a valid char in TicTacToe")
}

private fun symbolToChar(s: TicTacToeSymbol?): Char? {
    if (s == TicTacToeSymbol.CIRCLE)
        return 'o'
    if (s == TicTacToeSymbol.CROSS)
        return 'x'
    if (s == TicTacToeSymbol.EMPTY)
        return null

    throw IllegalArgumentException("Not a TicTacToeSymbol")
}
}
