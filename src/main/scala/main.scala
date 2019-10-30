import CardType._
import GameFns._
import IoUtils._

import scala.annotation.tailrec

object main extends App {

  var initState: State = createInitialState(
    numBlanks = 16,
    numExplosives = 1,
    numDefuses = 3,
    numDefusesPerPlayer = 1)

  runLoop(initState)

  @tailrec
  def runLoop(state: State): Any = {

    showPrompt()
    val userIn = getUserInput

    userIn match {
      case "D" =>
        val stateAfterDraw = draw(state)
        showState(stateAfterDraw)

        if (stateAfterDraw.gameIsOver) {
          if (stateAfterDraw.currentPlayer.lastDrawnCard.contains(Explode))
            showLoser()
          showGameOver()
        }
        else
          runLoop(stateAfterDraw)
      case "Q" =>
        showGameOver()
      case _ =>
        showUnrecognised()
        runLoop(state)

    }
  }
}



