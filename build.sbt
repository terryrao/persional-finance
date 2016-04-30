name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"


libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "Local Maven Repository" at "file://C:/Users/terryrao/.m2/repository"
resolvers += "OSChina Maven Repository" at "http://maven.oschina.net/content/groups/public/"
resolvers += "activator  Repository" at "file://D:/Program Files (x86)/typesafe-activator-1.3.7/activator-dist-1.3.7/repository"
externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)
