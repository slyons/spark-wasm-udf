package org.apache.spark.sql

import org.apache.spark.sql.catalyst.expressions.{Expression, TypedWASMUDFExpression}
import org.apache.spark.sql.types.DataType

object CustomFunctions {
  private def withExpr(expr: Expression): Column = Column(expr)

  def WASM_UDF(url: String, funcName: String, returnType: DataType, children: Column*) = withExpr {
    TypedWASMUDFExpression(url, funcName, returnType, children.map(_.expr): _*)
  }
}
