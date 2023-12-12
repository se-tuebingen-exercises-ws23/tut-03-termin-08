scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "cask" % "0.9.1"
)

run / fork := false
Global / cancelable := true
