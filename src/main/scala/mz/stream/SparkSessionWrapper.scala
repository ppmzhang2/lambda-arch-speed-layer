package mz.stream

import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {
  lazy val spark: SparkSession = SparkSession.builder
    .master(sparkMaster)
    .appName(sparkAppName)
    .getOrCreate
}
