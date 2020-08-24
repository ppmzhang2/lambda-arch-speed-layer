package mz

package object stream {
  val sparkMaster = "local[*]"
  val sparkAppName = "Structured Streaming from Kafka"
  val kafkaTopic = "trades"
  val kafkaBootstrapServers: String = "localhost:9092"
  val kafkaStreamInterval = "30 seconds"
  val kafkaStreamOutputMode = "update"
  val parquetPath = "/"
}
