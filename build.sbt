name := """shorten-url"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava).settings(
  watchSources ++= (baseDirectory.value / "public/ui" ** "*").get
)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.199"

libraryDependencies += "org.mongodb.morphia" % "morphia" % "1.3.2"
libraryDependencies += "org.mongodb.morphia" % "morphia-validation" % "1.3.2"

routesGenerator := InjectedRoutesGenerator