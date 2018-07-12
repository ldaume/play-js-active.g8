name := "$name$"

version := "$version$"

lazy val root = (project in file(".")).enablePlugins(SbtWeb, PlayJava, JavaAppPackaging, DockerPlugin)

scalaVersion := "2.12.6"


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "11")
    sys.error("Java 11 is required for this project. Found " + sys.props("java.specification.version"))
}

TwirlKeys.constructorAnnotations += "@javax.inject.Inject()"
