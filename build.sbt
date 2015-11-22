name := "play-js-active"

version := "0.0.1-SNAPSHOT"

lazy val `play-js-active` = (project in file(".")).enablePlugins(PlayJava, JavaAppPackaging, DockerPlugin)

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
  "com.jayway.jsonpath" % "json-path" % "2.0.0",
  
  // Commons
  "org.apache.commons" % "commons-lang3" % "3.4",
  "com.google.guava" % "guava" % "19.0-rc2",
  "org.apache.commons" % "commons-collections4" % "4.0",
  "commons-io" % "commons-io" % "2.4",
  
  // Json
  "com.jayway.jsonpath" % "json-path" % "2.0.0",
  
  // Testing
  "org.assertj" % "assertj-core" % "3.1.0" % "test"
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


// --------------------
// ------ DOCKER ------
// --------------------
// build with activator:publishLocal

// setting a maintainer which is used for all packaging types</pre>
maintainer := "Me"

// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)

// publish to repo
//dockerRepository := Some("quay.io/")
//dockerUpdateLatest := true

// run this with: docker run -p 9000:9000 <name>:<version>
