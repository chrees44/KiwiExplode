import CardType._

object GameFns {

  def createDeck(numBlanks: Int,
                 numExplosives: Int): List[CardType.Value] = {
    val blanks = List.fill(numBlanks)(Blank)
    val explosives = List.fill(numExplosives)(Explode)
    blanks ++ explosives
  }

//  def deal(state: State): State = {
//    state.copy()
//  }

  def draw(state: State): State = {
    state.deck match {
      case d :: r =>
        println(s"You drew a card: $d, remaining deck: ${r.length}")

        val stateAfterDraw = state.copy(deck = r, lastPlayedCard = Some(d))
        d match {
          case Explode =>
            stateAfterDraw.copy(gameIsOver = true)
          case Blank | _ =>
            if (r == Nil)
              stateAfterDraw.copy(gameIsOver = true)
            else stateAfterDraw
        }
      case Nil =>
        state.copy(deck = Nil, gameIsOver = true)
    }
  }

}
