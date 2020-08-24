name := "lambda-arch-stream"

version := "0.1"

scalaVersion := "2.12.10"

val sparkVersion = "3.0.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-token-provider-kafka-0-10" % sparkVersion,
  "com.github.luben" % "zstd-jni" % "1.4.4-3",
  "org.apache.kafka" % "kafka-clients" % "2.4.1",
  "org.lz4" % "lz4-java" % "1.4.0",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "org.spark-project.spark" % "unused" % "1.0.0",
  "org.xerial.snappy" % "snappy-java" % "1.1.7.5",
)
