import GameFns._

object Main extends App {

  val initState: State = createInitialState()

  val mainLoop = new MainLoop(new IoUtils)
  mainLoop.runLoop(initState)

}



