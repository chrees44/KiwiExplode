case class State(deck: List[CardType.Value],
                 lastPlayedCard: Option[CardType.Value],
                 gameIsOver: Boolean)
