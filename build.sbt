name := "play-js-active"

version := "0.0.1-SNAPSHOT"

lazy val `play-js-active` = (project in file(".")).enablePlugins(PlayJava)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.11.7"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  cache,
  javaWs,
  // WebJars pull in client-side web libraries,
  "org.webjars" % "bootstrap" % "3.3.5",
  "org.webjars" % "webjars-play_2.11" % "2.4.0-1",
  "org.webjars" % "jquery" % "3.0.0-alpha1",
  "org.webjars" % "requirejs" % "2.1.20",
  "org.webjars" % "backbonejs" % "1.2.1",
  "org.webjars" % "angularjs" % "2.0.0-alpha.22",
  //"org.webjars" % "underscorejs" % "1.8.1",
  "org.webjars" % "react" % "0.13.3",
  // Testing
  "org.assertj" % "assertj-core" % "3.1.0" % "test",
  "org.apache.commons" % "commons-lang3" % "3.4"
  // Select Play modules
  //anorm,     // Scala RDBMS Library
  //javaJpa,   // Java JPA plugin
  //"org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  //"mysql" % "mysql-connector-java" % "5.1.36",
  //filters,   // A set of built-in filters
  )

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}