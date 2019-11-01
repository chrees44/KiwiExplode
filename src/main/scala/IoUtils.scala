import scala.collection.MapView
import scala.io.StdIn

class IoUtils {
  def showPrompt(): Unit = println("(d)raw or (q)uit: ")

  def showState(state: State): Unit = {
//    println(s"You drew a card: ${state.currentPlayer.lastDrawnCard.getOrElse("")}, deck: ${getCardsGrouped(state.deck)}, player ${getCardsGrouped(state.currentPlayer.hand)}")
    println(s"You drew a card: ${state.currentPlayer.lastDrawnCard.getOrElse("")}, your hand: ${getCardsGrouped(state.currentPlayer.hand)}")
  }

  def showGameOver(): Unit = println("Game Over")

  def showUnrecognised(): Unit = println("Unrecognised Command")

  def showLoser(): Unit = println("BANG! You exploded")

  def getUserInput: String = StdIn.readLine.trim.toUpperCase

  def getCardsGrouped(cards: List[CardType.Value]): String = {
    val grouped: MapView[CardType.Value, Int] = cards.groupBy(identity).view.mapValues(_.length)
    CardType.values.toList.foldLeft(List[String]())((agg, next) =>
      agg :+ (grouped.getOrElse(next, 0).toString + next.toString.take(1))
    ).mkString(", ")
  }
}
