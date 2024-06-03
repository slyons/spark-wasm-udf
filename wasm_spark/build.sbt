
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "spark-wasm-udf"
  )

libraryDependencies ++= Seq (
  "org.apache.spark" %% "spark-sql" % "3.5.1" % "provided",
  "com.github.alexarchambault" % "case-app_2.12" % "2.1.0-M26",
  "org.extism.sdk" % "extism" % "1.0.0",
  "com.dylibso.chicory" % "runtime" % "0.0.10",
  "com.dylibso.chicory" % "wasi" % "0.0.10",
  "com.google.protobuf" % "protobuf-java" % "4.26.1",
)

Compile / run := Defaults.runTask(Compile / fullClasspath, Compile / run / mainClass, Compile / run / runner).evaluated
//run / Compile := Defaults.runTask(fullClasspath / Compile, mainClass / (Compile, run), runner / (Compile, run)).evaluated
Compile / runMain := Defaults.runMainTask(Compile / fullClasspath, Compile / run / runner).evaluated
//runMain in Compile := Defaults.runMainTask(fullClasspath / Compile, runner /(Compile, run)).evaluated
//unmanagedJars in Compile += file("./chicory-sdk-1.0-SNAPSHOT.jar")