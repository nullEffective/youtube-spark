/*
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Dataset, Encoders, RelationalGroupedDataset, Row, SparkSession, TypedColumn}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.*
import org.apache.spark.sql.expressions.scalalang.typed
import org.apache.spark.sql.types.StructType
//package org.apache.spark.examples.sql.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import java.sql.Timestamp
import java.util.Properties
import org.apache.spark.sql.catalyst.ScalaReflection
 */

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Dataset, Encoders, RelationalGroupedDataset, Row, SparkSession, TypedColumn}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.*
import org.apache.spark.sql.expressions.scalalang.typed
import org.apache.spark.sql.types.StructType
//package org.apache.spark.examples.sql.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import java.sql.Timestamp
import java.util.Properties
import org.apache.spark.sql.catalyst.ScalaReflection


object Sol {

  def msg = "I was compiled by Scala 3. :)"

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local[1]")
      .appName("myApp")
      //.config("master", "local[*]")
      //.master("local[*]")
      .getOrCreate()

    val sqlContext = spark.sqlContext

    /*
    val df = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "youtube-raw")
      .load()
    df.printSchema()

     */

    //val encoderSchema = Encoders.product[SimpleEvent]
    //encoderSchema.printTreeString()
    val encoder2 = Encoders.tuple(Encoders.STRING, Encoders.TIMESTAMP)

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "youtube-raw")
      .option("startingOffsets", "earliest") // From starting
      .option("inferSchema", true)
      .load()
      .select("topic", "timestamp")
      .as(encoder2)
      .map(row -> println(row))
    //      .load().select("key", "value", "timestamp")
    df.printSchema()

    import spark.implicits._

    val s = encoder2.schema
    val ds = df.to(s)
    println(ds)

    //    df.foreach(f => println(f))

    println("Hello world! ny")
  }

  case class SimpleEvent(key: String, value: String, timestamp: String)

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
*/

//(String, String, String)

/*
    val words = df.as[encoder2]
  .flatMap(line =>
      //line._1.split(" ").map(word => (word, line._2))
      ("foo", "bar", "Timestamp")
    ).toDF("key", "value", "timestamp")
    val v: RelationalGroupedDataset = df.groupBy(
      window($"timestamp", "10 minutes", "5 minutes"),
      $"word"
    )
    println(v.max("timestamp"))
    println(v)
*/
