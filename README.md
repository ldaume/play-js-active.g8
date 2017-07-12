# Abstract
A java [Play](https://www.playframework.com/) 2.6.0-M1 [Giter8](http://www.foundweekends.org/giter8/) template with JsFrameworks.

## Usage
`sbt new ldaume/play-js-active.g8 --name=play-js-active`

## Introduction
A starting template if you want to develop a micro service with javascript frameworks.

Then, we have the following objectives:

  * Development should be simple. `activator run` should be enough to run all services at the same time.
  * Common code, dependencies and modules should be easily shared.
  * We should be able to compile, test and run each service separately in development and production.
  * We should distribute each service separately.
  * It should be a template ready to use with the following features:
    * [Webjars](http://www.webjars.org).
    * [CoffeeScript](http://coffeescript.org) and [LESS](http://lesscss.org) Assets.
    * [Assets with AngularJS, Bootstrap, RequireJS, Backbone.js, React](http://www.playframework.com/documentation/2.4.x/Assets).
      * More could be: [Digest](http://www.coding-stories.com/digest-js/) or [Fingerprintjs2](https://github.com/Valve/fingerprintjs2)
  * It shoud explain:
    * How to share every common code to avoid duplications (models, controllers, views, CoffeeScript, LESS, ...).
    * How to use it for development, test and production.


## Logging

The logger is configured for a better output and contains a rolling file
appender. The logs are look like `2015-08-17 10:21:17,914 INFO
[ForkJoinPool-1-worker-3] application: Class::Method:#Line - Message`.
