import CardType._

import scala.util.Random

object GameFns {

  def createInitialState(numBlanks: Int,
                         numExplosives: Int,
                         numDefuses: Int,
                         numDefusesPerPlayer: Int): State = {
    val player = Player(
      hand = List.fill(numDefusesPerPlayer)(Defuse),
      lastDrawnCard = None
    )

    val deck = Random.shuffle(
      createDeck(numBlanks, numExplosives, numDefuses - 1))

    State(
      deck = deck,
      currentPlayer = player,
      gameIsOver = false)
  }

  def createDeck(numBlanks: Int,
                 numExplosives: Int,
                 numDefuses: Int): List[CardType.Value] = {
    val blanks = List.fill(numBlanks)(Blank)
    val explosives = List.fill(numExplosives)(Explode)
    val defuses = List.fill(numDefuses)(Defuse)
    blanks ++ explosives ++ defuses
  }

  //  def deal(state: State): State = {
  //    state.copy()
  //  }

  def draw(state: State): State = {
    state.deck match {
      case d :: r =>
        val stateAfterDraw = state.copy(
          deck = r,
          currentPlayer = state.currentPlayer.copy(
            lastDrawnCard = Some(d))
        )
        d match {
          case Explode =>
            drawExplode(stateAfterDraw)
          case Defuse =>
            stateAfterDraw.copy(
              currentPlayer = stateAfterDraw.currentPlayer.copy(
                hand = stateAfterDraw.currentPlayer.hand :+ Defuse
              ))
          case Blank | _ =>
            if (r == List.empty)
              stateAfterDraw.copy(gameIsOver = true)
            else stateAfterDraw
        }
      case List() =>
        state.copy(deck = List.empty, gameIsOver = true)
    }
  }

  private def drawExplode(state: State): State = {
    val player = state.currentPlayer
    if (player.hand.contains(Defuse)) {

      val updatedPlayer = player.copy(
        hand = player.hand.diff(List(Defuse)))

      val updatedDeck = Random.shuffle(state.deck :+ Explode)

      state.copy(deck = updatedDeck, currentPlayer = updatedPlayer)
    } else
      state.copy(gameIsOver = true)
  }

}
