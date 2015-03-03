scalaVersion in ThisBuild := "2.11.5"
libraryDependencies ++= Seq(
  "com.typesafe.sbt" % "client-all-2-11" % "0.3.2",
  "org.scala-lang.modules" %% "scala-pickling" % "0.10.0"
)
initialCommands in console := """
  import scala.pickling.Defaults._, scala.pickling.json._
  import java.io.File
  import picklers._

  val att = Attributed(new File("/a/b/c"))
"""
