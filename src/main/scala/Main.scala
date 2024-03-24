//import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import org.apache.spark.sql.SparkSession
import java.util.Properties

object Sol {

  def msg = "I was compiled by Scala 3. :)"

  def main(args: Array[String]): Unit = {

    val requestURL = "https://api.coinbase.com/v2/exchange-rates?currency=USD"

    val spark = SparkSession
      .builder()
      .master("local[1]")
      .appName("myApp")
      //.config("master", "local[*]")
      //.master("local[*]")
      .getOrCreate()
    spark.setl

    val df = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "youtube-raw")
      .load()
    df.printSchema()

    val dfReadStream = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "youtube-raw")
      .option("startingOffsets", "earliest") // From starting
      .load()
    dfReadStream.printSchema()


    val sqlContext = spark.sqlContext
    import spark.implicits._ //sqlContext OR spark implicits
    //    df = df.as[(Long,String, String)]

    //"CAST(key AS STRING)", 
    val r = df.selectExpr("CAST(value AS STRING)").as[String]
    for (x <- r) {
      println(x)
    }
    //dataFrame.show()//.show(10)
    //val producer = createProducer()
    println("Hello world! ny")


  }
  /*
    def createProducer(): KafkaProducer[String, String] = {

      val props: Properties = new Properties()
      props.put("bootstrap.servers", "localhost:9092")
      props.put("key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer")
      props.put("value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer")
      props.put("acks", "all")
      val producer = new KafkaProducer[String, String](props)
      val topic = "youtube-raw"
      producer.send(ProducerRecord[String, String](topic, "foo", "bar"))
      //  producer.close()
      producer
    }
  */
}