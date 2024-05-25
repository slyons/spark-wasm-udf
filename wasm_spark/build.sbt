
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "spark-wasm-udf"
  )

/*Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)*/

ThisBuild / assemblyMergeStrategy := {
  case "google/protobuf/any.proto" | "google/protobuf/descriptor.proto" => MergeStrategy.first
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

libraryDependencies ++= Seq (
  "org.apache.spark" %% "spark-sql" % "3.5.1" % "provided",
  "org.apache.spark" %% "spark-connect" % "3.5.1",
  "org.extism.sdk" % "extism" % "1.0.0",
  "com.google.protobuf" % "protobuf-java" % "4.26.1",
  //"org.apache.arrow" % "arrow-vector" % "9.0.0",
  //"org.apache.arrow" % "arrow-memory-netty" % "9.0.0"
)