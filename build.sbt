name := "scala-benchmarking-template"

organization := "com.example"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  "com.google.caliper" % "caliper" % "0.5-rc1",
  "com.google.code.java-allocation-instrumenter" % "java-allocation-instrumenter" % "2.1",
  "com.google.code.gson" % "gson" % "2.2.2")

fork in run := true

// we need to add the runtime classpath as a "-cp" argument to the `javaOptions in run`, otherwise caliper
// will not see the right classpath and die with a ConfigurationException
javaOptions in run <++= (fullClasspath in Runtime) map { cp => Seq("-cp", sbt.Build.data(cp).mkString(":")) }
