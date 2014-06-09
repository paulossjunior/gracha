name := "gracha"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.apache.jena" % "apache-jena-libs" % "2.11.1"
)     

play.Project.playJavaSettings
