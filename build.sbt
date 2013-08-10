// ---------------------------------------------------------------------------
// Basic settings

organization := "org.clapper"

name := "cchs"

version := "0.1.1"

scalaVersion := "2.10.0"

licenses := Seq("BSD" -> url("http://software.clapper.org/cchs/license.html"))

homepage := Some(url("http://software.clapper.org/cchs/"))

description := "Quick and dirty character encoding converter"

// ---------------------------------------------------------------------------
// Plugin settings

seq(conscriptSettings :_*)

// sbt-site and sbt-ghpages

site.settings

ghpages.settings

git.remoteRepo := "git@github.com:bmc/cchs.git"

site.jekyllSupport()

com.typesafe.sbt.site.JekyllSupport.RequiredGems := Map(
  "jekyll" -> "0.11.2",
  "liquid" -> "2.3.0"
)

// sbt-buildinfo

buildInfoSettings

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)

buildInfoPackage := "org.clapper.cchs"

// sbt-editsource

seq(EditSource.settings: _*)

sources in EditSource.Config <++= baseDirectory.map(d =>
  (d / "src" / "main" / "conscript-template" / "cchs" / "launchconfig").get
)

EditSource.targetDirectory in EditSource.Config <<= baseDirectory(_ / "src" / "main" / "conscript" / "cchs")

EditSource.flatten in EditSource.Config := true

EditSource.variables in EditSource.Config <+= version {v => ("version" -> v)}

// ---------------------------------------------------------------------------
// Dependencies

libraryDependencies ++= Seq(
  "org.clapper" %% "grizzled-scala" % "1.1.3"
)

// ---------------------------------------------------------------------------
// Publishing criteria

publishTo := Some(Opts.resolver.sonatypeStaging)

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:bmc/cchs.git/</url>
    <connection>scm:git:git@github.com:bmc/cchs.git</connection>
  </scm>
  <developers>
    <developer>
      <id>bmc</id>
      <name>Brian Clapper</name>
      <url>http://www.clapper.org/bmc</url>
    </developer>
  </developers>
)
