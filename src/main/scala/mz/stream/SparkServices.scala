package mz.stream

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import javax.inject.Singleton
import mz.stream.models.{RawTrade, Trade}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.Trigger


@Singleton
class SparkServices extends SparkSessionWrapper {

  import spark.implicits._

  def streamPersist(): Unit = {
    kafkaSource.writeStream.foreachBatch {
      (batchDF: DataFrame, batchId: Long) => {
        val ds = batchDF
          .selectExpr("CAST(value AS STRING) as data_string")
          .as[String]
          .map {
            x =>
              x.replaceAll("\"", "")
                .split(",").map(_.trim).toSeq
          }
          .map(r => RawTrade(r.head, r(1), r(2), r(3)))
          .map(t => Trade(t.price.toFloat, t.symbol, t.millisecond.toLong,
            t.volume.toFloat))

        ds.persist()
        ds.write.format("parquet")
          .save(path = s"$parquetPath/pq_${batchId.toString}_${timeString()}")
        ds.unpersist()
        println(batchId.toString + " saved")
      }
    }
      .queryName("trade_data")
      .trigger(Trigger.ProcessingTime(kafkaStreamInterval))
      .outputMode(kafkaStreamOutputMode)
      .start()
      .awaitTermination()
  }

  private def timeString(): String = {
    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
      .format(LocalDateTime.now)
  }

  private def kafkaSource: DataFrame = {
    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBootstrapServers)
      .option("subscribe", kafkaTopic)
      .load()
  }

}
