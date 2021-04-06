name := """SHORTEN-URL"""
organization := "com.papagroup"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies += guice
PlayKeys.devSettings := Seq("play.server.http.port" -> "8080")
