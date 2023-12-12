object Server extends ServerBase {

  case class Tuet(message: String, time: java.time.LocalTime, likes: Int)

  var tuets: List[Tuet] = Tuet("first message", java.time.LocalTime.now(), 1) :: Nil

  @cask.get("/feed")
  def feed() = {
    val lines = tuets.map { case Tuet(message, time, likes) =>
      s"[$time] $message ($likes 👍)"
    }
    lines.mkString("\n\n")
  }

  @cask.post("/tuet")
  def tuet(request: cask.Request) = {
    val message = request.text()
    val time = java.time.LocalTime.now()
    val likes = 0
    tuets = Tuet(message, time, likes) :: tuets
  }
  @cask.post("/like")
  def like() = {
    ???
  }

  initialize()

}
