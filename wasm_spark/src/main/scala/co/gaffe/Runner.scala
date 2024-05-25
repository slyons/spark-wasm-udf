package co.gaffe

import co.gaffe.proto.Wasmudf
import org.apache.spark.sql.CustomFunctions.WASM_UDF
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit}
import org.apache.spark.sql.types.{IntegerType, StringType}

object Runner extends App{
  if (args.length != 2) {
    println("Usage: spark-submit <options> <wasm_path_or_url> <column_name>")
    System.exit(1)
  }

  val session = SparkSession.builder().master("local[*]")
    .getOrCreate()

  val pathOrUrl = args(0)
  val column_name = args(1)
  println(s"WASMUDF: Loading WASM file ${pathOrUrl}")

  val df = session.range(10)
    .withColumn(column_name,
      WASM_UDF(
        pathOrUrl,
        "add_two",
        IntegerType,
        col("id").cast(IntegerType),
        lit(2)

      //WASM_UDF(pathOrUrl, "add_two_str", StringType, col("id"), lit(2)
    ))
  df.show()
}
