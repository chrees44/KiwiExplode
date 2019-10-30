import CardType._
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class GameFnsTest extends FlatSpec {

  "createDeck" should "add only Blanks" in {
    val deck = GameFns.createDeck(3, 0)
    deck should have length 3
    deck should contain only Blank
  }

  it should "add only Explosives" in {
    val deck = GameFns.createDeck(0, 5)
    deck should have length 5
    deck should contain only Explode
  }

  it should "add mixed card types" in {
    val deck = GameFns.createDeck(5, 2)
    deck should have length 7
    exactly(5, deck) should equal(Blank)
    exactly(2, deck) should equal(Explode)
    deck should contain only(Blank, Explode)
  }

  "draw" should "handle deck is Nil" in {
    val state = State(deck = Nil, lastPlayedCard = None, gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck shouldEqual Nil
  }

  it should "handle next card is Blank" in {
    val state = State(deck = List(Blank, Blank), lastPlayedCard = None, gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (List(Blank))
    result.lastPlayedCard should equal(Some(Blank))
    result.gameIsOver should equal(false)
  }

  it should "handle next card is Explode" in {
    val state = State(deck = List(Explode, Blank), lastPlayedCard = None, gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (List(Blank))
    result.lastPlayedCard should equal(Some(Explode))
    result.gameIsOver should equal(true)
  }

  it should "handle last card is Blank" in {
    val state = State(deck = List(Blank), lastPlayedCard = None, gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (Nil)
    result.lastPlayedCard should equal(Some(Blank))
    result.gameIsOver should equal(true)
  }

  it should "handle last card is Explode" in {
    val state = State(deck = List(Explode), lastPlayedCard = None, gameIsOver = false)

    val result = GameFns.draw(state)

    result.deck should equal (Nil)
    result.lastPlayedCard should equal(Some(Explode))
    result.gameIsOver should equal(true)
  }

}
