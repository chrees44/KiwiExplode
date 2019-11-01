import CardType._
import GameFns.createInitialState
import org.mockito.Mockito._
import org.scalatest.Matchers._
import org.scalatest.{BeforeAndAfter, FlatSpec}
import org.scalatestplus.mockito.MockitoSugar

class MainLoopTest extends FlatSpec with BeforeAndAfter with MockitoSugar {

  private val mockIoUtils: IoUtils = mock[IoUtils]

  before {
    reset(mockIoUtils)
  }

  private val baseState: State = createInitialState()
  private val mainLoop: MainLoop = new MainLoop(mockIoUtils)

  "runLoop" should "show prompt and get user input" in {
    when(mockIoUtils.getUserInput).thenReturn("Q")

    mainLoop.runLoop(baseState)

    verify(mockIoUtils).showPrompt()
    verify(mockIoUtils).getUserInput
  }

  it should "draw Blank and continue" in {
    val state = baseState.copy(deck = List(Blank))

    when(mockIoUtils.getUserInput).thenReturn("D", "Q")

    mainLoop.runLoop(state)

    verify(mockIoUtils, times(2)).getUserInput
  }

  it should "draw Explode and lose" in {
    val state = baseState.copy(
      deck = List(Explode),
      currentPlayer = baseState.currentPlayer.copy(hand = List.empty))

    when(mockIoUtils.getUserInput).thenReturn("D")

    mainLoop.runLoop(state)

    verify(mockIoUtils).getUserInput
    verify(mockIoUtils).showLoser()
    verify(mockIoUtils).showGameOver()
  }

  it should "draw Explode and not lose" in {
    val state = baseState.copy(deck = List(Explode))

    when(mockIoUtils.getUserInput).thenReturn("D", "Q")

    mainLoop.runLoop(state)

    verify(mockIoUtils, times(2)).getUserInput
  }

  it should "draw Defuse then Explode and not lose" in {
    val state = baseState.copy(
      deck = List(Defuse, Explode),
      currentPlayer = baseState.currentPlayer.copy(hand = List.empty))

    when(mockIoUtils.getUserInput).thenReturn("D", "D", "Q")

    mainLoop.runLoop(state)

    verify(mockIoUtils, times(3)).getUserInput
  }

  it should "handle unrecognised input" in {
    val state = baseState.copy()

    when(mockIoUtils.getUserInput).thenReturn("X", "Q")

    mainLoop.runLoop(state)

    verify(mockIoUtils, times(2)).getUserInput
  }
}


