import scala.io.StdIn

object IoUtils {
  def showPrompt(): Unit = println("(d)raw or (q)uit: ")

  def showGameOver(): Unit = println("Game Over")

  def showUnrecognised(): Unit = println("Unrecognised Command")

  def showLoser(): Unit = println("BANG! You exploded")

  def getUserInput: String = {
    StdIn.readLine.trim.toUpperCase
  }
}
