package org.apache.spark.sql.catalyst.expressions

import co.gaffe.WASMPluginCache
import co.gaffe.proto.UDFArgs
import co.gaffe.proto.{Literal => ConnectLiteral}
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.expressions.codegen.CodegenFallback
import org.apache.spark.sql.connect.common.LiteralValueProtoConverterV2
import org.apache.spark.sql.types.DataType

case class TypedWASMUDFExpression(urlOrPath: String, funcName: String, returnType: DataType, children: Expression*)
  extends Expression with CodegenFallback {

  override def prettyName: String = funcName

  override lazy val deterministic: Boolean = false

  override def nullable: Boolean = true

  override def eval(input: InternalRow): Any = {
    val argBuilder = UDFArgs.newBuilder()
    var i = 0
    while(i < children.length) {
      val expr = children(i)
      val lit = LiteralValueProtoConverterV2.toLiteralProto(expr.eval(input))
      argBuilder.addArgs(lit)
      i += 1
    }
    val argBytes = argBuilder.buildPartial().toByteArray
    val plugin = WASMPluginCache.getPlugin(urlOrPath, true)
    val responseBytes = plugin.call(funcName, argBytes)
    val asLiteral = ConnectLiteral.parseFrom(responseBytes)
    LiteralValueProtoConverterV2.toCatalystValue(asLiteral)
  }

  override def dataType: DataType = returnType

  override protected def withNewChildrenInternal(newChildren: IndexedSeq[Expression]): Expression =
    TypedWASMUDFExpression(urlOrPath, funcName, returnType, newChildren:_*)
}
