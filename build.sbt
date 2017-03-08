import com.typesafe.sbt.packager.docker._

name := "play-js-active"

version := "0.0.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, JavaAppPackaging, DockerPlugin)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.12.1"

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.jcenterRepo,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)

libraryDependencies ++= Seq(
  cache,
  javaWs,
  // WebJars pull in client-side web libraries,
  "org.webjars" % "bootstrap" % "3.3.7",
  "org.webjars" %% "webjars-play" % "2.6.0-M1",
  "org.webjars" % "jquery" % "3.1.1",
  "org.webjars" % "requirejs" % "2.3.2",
  "org.webjars" % "backbonejs" % "1.3.2",
  "org.webjars" % "angularjs" % "2.0.0-alpha.22",
  //"org.webjars" % "underscorejs" % "1.8.1",
  "org.webjars" % "react" % "15.3.2",

  // Commons
  "org.apache.commons" % "commons-lang3" % "3.5",
  "com.google.guava" % "guava" % "21.0",
  "org.apache.commons" % "commons-collections4" % "4.1",
  "commons-io" % "commons-io" % "2.5",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.2.0",

  // Testing
  "org.assertj" % "assertj-core" % "3.6.2" % "test"
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

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

LessKeys.compress in Assets := true

pipelineStages := Seq(digest, gzip)

includeFilter in (Assets, LessKeys.less) := "*.less"

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

dependencyUpdatesFailBuild := true
dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang") /*| moduleFilter(organization = "org.scala-lang", name = "twirl-api")*/

TwirlKeys.constructorAnnotations += "@javax.inject.Inject()"


// --------------------
// ------ DOCKER ------
// --------------------
// build with activator docker:publishLocal

// change to smaller base image
dockerBaseImage := "frolvlad/alpine-oraclejdk8:latest"
dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(cmd, Cmd("RUN", "apk update && apk add bash"))
  case other => List(other)
}

// setting a maintainer which is used for all packaging types</pre>
maintainer := "Me"

// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)

// publish to repo
//dockerRepository := Some("quay.io/")
//dockerUpdateLatest := true

// run this with: docker run -p 9000:9000 <name>:<version>
