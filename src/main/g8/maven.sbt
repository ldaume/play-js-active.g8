credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.jcenterRepo,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)
