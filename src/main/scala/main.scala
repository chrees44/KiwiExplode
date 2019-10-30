import CardType._
import GameFns._
import IoUtils._

import scala.annotation.tailrec
import scala.util.Random

object main extends App {

  var initState: State = State(
    Random.shuffle(
      createDeck(numBlanks = 16, numExplosives = 1)),
    lastPlayedCard = None,
    gameIsOver = false)

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
          if (stateAfterDraw.lastPlayedCard.contains(Explode))
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



