package mz.stream

object StreamConsumer extends App {
  val ss = new SparkServices()
  val jsonUtils = new JsonUtils()

  def main(): Unit = {
    ss.streamPersist()
  }

  main()
}
