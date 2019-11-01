name := "KiwiExplode"

version := "0.1"

scalaVersion := "2.13.0"

logLevel := Level.Info

scalacOptions ++= Seq("-Xlint", "-Xfatal-warnings")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "org.mockito" %% "mockito-scala-scalatest" % "1.7.0" % "test"