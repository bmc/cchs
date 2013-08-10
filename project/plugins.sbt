resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

addSbtPlugin("net.databinder" % "conscript-plugin" % "0.3.5")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.2.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.6.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.0")

addSbtPlugin("org.clapper" % "sbt-editsource" % "0.6.5")
