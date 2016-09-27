import sbt.Keys._
import sbt._

object Build extends sbt.Build {  
  val pico_event                = "org.pico"        %%  "pico-event"                % "2.0.1"

  val specs2_core               = "org.specs2"      %%  "specs2-core"               % "3.7.2"
  val rxscala                   = "io.reactivex"    %%  "rxscala"                   % "0.26.2"

  implicit class ProjectOps(self: Project) {
    def standard(theDescription: String) = {
      self
          .settings(scalacOptions in Test ++= Seq("-Yrangepos"))
          .settings(publishTo := Some("Releases" at "s3://dl.john-ky.io/maven/releases"))
          .settings(description := theDescription)
          .settings(isSnapshot := true)
          .settings(resolvers += Resolver.sonatypeRepo("releases"))
          .settings(addCompilerPlugin("org.spire-math" % "kind-projector" % "0.8.0" cross CrossVersion.binary))
    }

    def notPublished = self.settings(publish := {}).settings(publishArtifact := false)

    def libs(modules: ModuleID*) = self.settings(libraryDependencies ++= modules)

    def testLibs(modules: ModuleID*) = self.libs(modules.map(_ % "test"): _*)
  }

  lazy val `pico-fake` = Project(id = "pico-fake", base = file("pico-fake"))
      .standard("Fake project").notPublished
      .testLibs(specs2_core)

  lazy val `pico-rx` = Project(id = "pico-rx", base = file("pico-rx"))
      .standard("Tiny publish-subscriber library")
      .libs(pico_event)
      .testLibs(specs2_core, rxscala)

  lazy val all = Project(id = "pico-rx-project", base = file("."))
      .notPublished
      .aggregate(`pico-rx`, `pico-fake`)
}
