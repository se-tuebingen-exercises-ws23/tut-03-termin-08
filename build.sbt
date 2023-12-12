// We have two separate projects:
// - A http server
// - A command line client to talk to the http server

// The http server
lazy val server = project.in(file("server"))

// The command line client
lazy val client = project.in(file("client"))

// enables nicer error messages
ThisBuild / scalacOptions += "-explain"
