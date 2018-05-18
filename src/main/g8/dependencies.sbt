

libraryDependencies ++= Seq(
  ehcache,
  javaWs,
  guice,

  // WebJars pull in client-side web libraries,
  "org.webjars" % "bootstrap" % "4.1.0",
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "jquery" % "3.3.1",
  "org.webjars" % "requirejs" % "2.3.5",
  "org.webjars" % "backbonejs" % "1.3.3",
  "org.webjars" % "angularjs" % "2.0.0-alpha.22",
  //"org.webjars" % "underscorejs" % "1.8.1",
  "org.webjars" % "react" % "16.3.2",

  // Commons
  "software.reinvent" % "commons" % "0.3.11",

  // Json
  "com.jayway.jsonpath" % "json-path" % "2.4.0",

  // Testing
  "org.assertj" % "assertj-core" % "3.10.0" % "test"
  // Select Play modules
  //anorm,     // Scala RDBMS Library
  //javaJpa,   // Java JPA plugin
  //"org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  //"mysql" % "mysql-connector-java" % "5.1.36",
  //filters,   // A set of built-in filters
)


dependencyUpdatesFailBuild := true

dependencyUpdatesFilter -=
  moduleFilter(organization = "org.scala-lang") |
    moduleFilter(organization = "com.typesafe.play", name = "twirl-api")
