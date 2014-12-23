name := """agile-book"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.hibernate" % "hibernate-core" % "4.3.0.CR1",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.0.CR1",
  "org.hibernate.javax.persistence" % "hibernate-jpa-2.1-api" % "1.0.0.Draft-16"
)
