import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "test-play"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
    "postgresql" % "postgresql" % "9.2-1002.jdbc4"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    ebeanEnabled := false
  )

}
