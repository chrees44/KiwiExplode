import CardType._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class GameFnsTest extends FlatSpec {

  private val player = Player(hand = List.empty, lastDrawnCard = None)

  private val baseState = State(
    deck = List.empty,
    currentPlayer = player,
    gameIsOver = false
  )

  "createDeck" should "add only Blanks" in {
    val deck = GameFns.createDeck(3, 0, 0)
    deck should have length 3
    deck should contain only Blank
  }

  it should "add only Explosives" in {
    val deck = GameFns.createDeck(0, 5, 0)
    deck should have length 5
    deck should contain only Explode
  }

  it should "add only Defuses" in {
    val deck = GameFns.createDeck(0, 0, 2)
    deck should have length 2
    deck should contain only Defuse
  }

  it should "add mixed card types" in {
    val deck = GameFns.createDeck(5, 2, 3)
    deck should have length 10
    exactly(5, deck) should equal(Blank)
    exactly(2, deck) should equal(Explode)
    exactly(3, deck) should equal(Defuse)
    deck should contain only(Blank, Explode, Defuse)
  }

  "createInitialState" should "throw if empty deck" in {

  }


  "draw" should "handle deck is empty List" in {
    val result = GameFns.draw(baseState)

    result.deck shouldEqual List.empty
    result.gameIsOver shouldEqual true
  }

  it should "handle next card is Blank" in {
    val state = baseState.copy(deck = List(Blank, Blank), gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (List(Blank))
    result.currentPlayer.lastDrawnCard should equal(Some(Blank))
    result.gameIsOver should equal(false)
  }

  it should "handle next card is Explode" in {
    val state = baseState.copy(deck = List(Explode, Blank), gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (List(Blank))
    result.currentPlayer.lastDrawnCard should equal(Some(Explode))
    result.gameIsOver should equal(true)
  }

  it should "handle last card is Blank" in {
    val state = baseState.copy(deck = List(Blank), gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (List.empty)
    result.currentPlayer.lastDrawnCard should equal(Some(Blank))
    result.gameIsOver should equal(true)
  }

  it should "handle last card is Explode" in {
    val state = baseState.copy(deck = List(Explode), gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (List.empty)
    result.currentPlayer.lastDrawnCard should equal(Some(Explode))
    result.gameIsOver should equal(true)
  }

}
