scalaVersion in ThisBuild := "2.11.5"
libraryDependencies ++= Seq(
  "com.typesafe.sbt" % "client-all-2-11" % "0.3.2",
  "org.scala-lang.modules" %% "scala-pickling" % "0.10.0"
)
initialCommands in console := """
  import java.io.File
  import sbt.serialization._

  val att = Attributed(new File("/a/b/c"))
  val p = SerializedValue(att)
  //val json = SerializedValue(att).toJsonString
  //val p = SerializedValue.fromJsonString(json)
  val u = p.parse[Attributed[File]]
"""
