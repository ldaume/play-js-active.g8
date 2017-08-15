logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
sbtPlugin := true

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.3")

// Web plugins
addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.8")
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.3.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")


addSbtPlugin("net.ground5hark.sbt" % "sbt-css-compress" % "0.1.4")
addSbtPlugin("com.slidingautonomy.sbt" % "sbt-html-minifier" % "1.0.0")
addSbtPlugin("org.danielnixon" % "sbt-uglify" % "1.0.7")

addSbtPlugin("net.ground5hark.sbt" % "sbt-closure" % "0.1.3")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.2.1")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.1")
