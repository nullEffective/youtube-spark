ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "youtube-spark",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
  )
val scala3Version = "3.3.1"
//conflictManager := ConflictManager.strict

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql-kafka-0-10
//https: //repo1.maven.org/maven2/org/apache/spark/spark-sql-kafka-0-10_2.13/3.5.1/
libraryDependencies += "org.apache.spark" % "spark-sql-kafka-0-10_2.13" % "3.5.1" //% Test

//libraryDependencies += "org.apache.spark" % "spark-core_2.13" % "3.5.1"
libraryDependencies += "org.apache.spark" % "spark-sql_2.13" % "3.5.1" //% "provided"  //SparkSession
//libraryDependencies += "org.apache.kafka" % "kafka-streams" % "3.7.0"
//libraryDependencies
//dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.15.1"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
//libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.7.0"

//libraryDependencies += "org.apache.spark" % "spark-sql-kafka-0-10_2.10" % "2.2.3"
//libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.10" % "2.2.3"

//dependencyOverrides += "org.apache.spark" % "spark-tags" % "2.13.0"

