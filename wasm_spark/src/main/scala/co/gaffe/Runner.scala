package co.gaffe

import co.gaffe.proto.Wasmudf
import org.apache.spark.sql.CustomFunctions.{CHICORY_WASM_UDF, WASM_UDF}
import org.apache.spark.sql.{Column, SparkSession}
import org.apache.spark.sql.functions.{col, lit}
import org.apache.spark.sql.types.{IntegerType, StringType}
import caseapp._

import java.util.concurrent.TimeUnit

case class Options(
  wasmPathOrUrl: String,
  noChicory: Option[Boolean] = None,
  runCount: Option[Int] = None,
  rowCount: Option[Int] = None
)

object Runner extends CaseApp[Options]{

  def run(options: Options, arg: RemainingArgs): Unit = {

    //System.setProperty("jna.library.path", "/usr/local/lib/libextism.dylib")

    val session = SparkSession.builder().master("local[*]")
      .getOrCreate()

    val pathOrUrl = options.wasmPathOrUrl
    val runCount = options.runCount.getOrElse(1)
    val noChicory = options.noChicory.getOrElse(false)
    val rowCount = options.rowCount.getOrElse(100)
    println(s"WASMUDF: Loading WASM file ${pathOrUrl}")

    val column = if (!noChicory) {
      CHICORY_WASM_UDF(
        pathOrUrl,
        "add_two",
        IntegerType,
        col("id").cast(IntegerType),
        lit(2)
      )
    } else {
      WASM_UDF(
        pathOrUrl,
        "add_two",
        IntegerType,
        col("id").cast(IntegerType),
        lit(2)
      )
    }

    val results = (0 to runCount).map(_ => {
      run_query(session, rowCount, column)
    })

    val min = TimeUnit.NANOSECONDS.toMillis(results.min)
    val max = TimeUnit.NANOSECONDS.toMillis(results.max)
    val avg = TimeUnit.NANOSECONDS.toMillis((results.sum / results.length))

    println("Final result")
    println(s"${runCount} Runs of ${rowCount}: ${min}ms/${max}ms/${avg}ms (Min/Max/Avg)")
  }

  def run_query(session:SparkSession, rowCount: Int, column: Column): Long = {
    val res = timeBlock {
      session.range(rowCount)
        .withColumn("wasm_udf", column)
        .collect()
    }
    res._2.foreach(x => println(x.prettyJson))
    res._1
  }

  def timeBlock[R](block: => R): (Long, R) = {
    val t0 = System.nanoTime()
    val result = block
    val t1 = System.nanoTime()
    (t1-t0, result)
  }
}
