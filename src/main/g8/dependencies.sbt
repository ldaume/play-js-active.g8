
val playVersion = "2.6.15"


val guiceVersion = "4.2.0"
val guiceDeps = Seq(
  "com.google.inject" % "guice" % guiceVersion,
  "com.google.inject.extensions" % "guice-assistedinject" % guiceVersion
)
libraryDependencies ++= Seq(
  ehcache,
  javaWs,
  guice,

  // jdk 11 workaround
  "javax.xml.bind" % "jaxb-api" % "2.3.0",

  // WebJars pull in client-side web libraries,
  "org.webjars" % "bootstrap" % "4.1.1",
  "org.webjars" % "bootstrap-notify" % "3.1.3",
  "org.webjars" %% "webjars-play" % "2.6.2",
  "org.webjars" % "jquery" % "3.3.1",
  "org.webjars" % "datatables" % "1.10.19",
  "org.webjars" % "momentjs" % "2.22.2",
  
  // Commons
  "software.reinvent" % "commons" % "0.3.12",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.4.0",

  // Testing
  "org.assertj" % "assertj-core" % "3.10.0" % "test"
) ++ guiceDeps


dependencyUpdatesFailBuild := true

dependencyUpdatesFilter -=
  moduleFilter(organization = "org.scala-lang") |
    moduleFilter(organization = "com.typesafe.play", name = "twirl-api") |
    moduleFilter(organization = "com.typesafe.play", name = "play-cache_2.12") |
    moduleFilter(organization = "org.webjars", name = "webjars-play") |
    moduleFilter(organization = "mysql")
