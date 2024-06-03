package org.apache.spark.sql

import co.gaffe.{ChicoryWASMPluginCache, WASMPluginCache}
import org.apache.spark.sql.catalyst.expressions.{Expression, WASMUDFExpression}
import org.apache.spark.sql.types.DataType

object CustomFunctions {
  private def withExpr(expr: Expression): Column = Column(expr)

  def WASM_UDF(url: String, funcName: String, returnType: DataType, children: Column*) = withExpr {
    WASMPluginCache.getPlugin(url, true);
    WASMUDFExpression(url, false, funcName, returnType, children.map(_.expr): _*)
  }

  def CHICORY_WASM_UDF(url: String, funcName: String, returnType: DataType, children: Column*) = withExpr {
    ChicoryWASMPluginCache.getPlugin(url, true)
    WASMUDFExpression(url, true, funcName, returnType, children.map(_.expr): _*)
  }
}
