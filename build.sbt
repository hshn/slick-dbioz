scalaVersion := "2.12.7"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.scalaz" %% "scalaz-core" % "7.2.26",
)
