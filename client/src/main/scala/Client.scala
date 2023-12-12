object Client extends App {

  println("Starting client.")

  while (true) {
    println("Enter a new message:")
    val message = Console.in.readLine()
    requests.post("http://localhost:8080/tuet/", data = message)
  }

}
