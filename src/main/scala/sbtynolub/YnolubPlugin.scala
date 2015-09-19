package sbtynolub

import sbt._
import Keys._
import scala.io.Source

object YnolubPlugin extends sbt.AutoPlugin {
  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  override lazy val projectSettings: Seq[Def.Setting[_]] = ynolubSettings

  lazy val ynolubSettings: Seq[sbt.Def.Setting[_]] = Seq(
    scalaOrganization := "com.eed3si9n",
    scalaVersion := "2.11.8-X1",
    scalacOptions += "-Yno-lub",
    resolvers += Resolver.bintrayRepo("eed3si9n", "maven")
  )
}
