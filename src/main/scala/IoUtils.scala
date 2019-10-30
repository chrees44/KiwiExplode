import scala.io.StdIn

object IoUtils {
  def showPrompt(): Unit = println("(d)raw or (q)uit: ")

  def showState(state: State): Unit = {
    println(s"You drew a card: ${state.lastPlayedCard.getOrElse("")}, remaining deck: ${state.deck.length}")
  }

  def showGameOver(): Unit = println("Game Over")

  def showUnrecognised(): Unit = println("Unrecognised Command")

  def showLoser(): Unit = println("BANG! You exploded")

  def getUserInput: String = StdIn.readLine.trim.toUpperCase
}
