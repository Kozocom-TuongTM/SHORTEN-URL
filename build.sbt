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
//libraryDependencies += "javax.el" % "javax.el-api" % "3.0.0"
libraryDependencies += "org.mongodb.morphia" % "morphia-validation" % "1.3.2"

// libraryDependencies ++= Seq(
//   "org.reactivemongo" %% "play2-reactivemongo" % "0.11.14"
// )
routesGenerator := InjectedRoutesGenerator