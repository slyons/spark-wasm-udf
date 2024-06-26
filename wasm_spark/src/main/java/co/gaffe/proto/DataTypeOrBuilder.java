// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: types.proto

// Protobuf Java Version: 4.26.1
package co.gaffe.proto;

public interface DataTypeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:co.gaffe.proto.DataType)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>.co.gaffe.proto.DataType.NULL null = 1;</code>
   * @return Whether the null field is set.
   */
  boolean hasNull();
  /**
   * <code>.co.gaffe.proto.DataType.NULL null = 1;</code>
   * @return The null.
   */
  co.gaffe.proto.DataType.NULL getNull();

  /**
   * <code>.co.gaffe.proto.DataType.Binary binary = 2;</code>
   * @return Whether the binary field is set.
   */
  boolean hasBinary();
  /**
   * <code>.co.gaffe.proto.DataType.Binary binary = 2;</code>
   * @return The binary.
   */
  co.gaffe.proto.DataType.Binary getBinary();

  /**
   * <code>.co.gaffe.proto.DataType.Boolean boolean = 3;</code>
   * @return Whether the boolean field is set.
   */
  boolean hasBoolean();
  /**
   * <code>.co.gaffe.proto.DataType.Boolean boolean = 3;</code>
   * @return The boolean.
   */
  co.gaffe.proto.DataType.Boolean getBoolean();

  /**
   * <pre>
   * Numeric types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Byte byte = 4;</code>
   * @return Whether the byte field is set.
   */
  boolean hasByte();
  /**
   * <pre>
   * Numeric types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Byte byte = 4;</code>
   * @return The byte.
   */
  co.gaffe.proto.DataType.Byte getByte();

  /**
   * <code>.co.gaffe.proto.DataType.Short short = 5;</code>
   * @return Whether the short field is set.
   */
  boolean hasShort();
  /**
   * <code>.co.gaffe.proto.DataType.Short short = 5;</code>
   * @return The short.
   */
  co.gaffe.proto.DataType.Short getShort();

  /**
   * <code>.co.gaffe.proto.DataType.Integer integer = 6;</code>
   * @return Whether the integer field is set.
   */
  boolean hasInteger();
  /**
   * <code>.co.gaffe.proto.DataType.Integer integer = 6;</code>
   * @return The integer.
   */
  co.gaffe.proto.DataType.Integer getInteger();

  /**
   * <code>.co.gaffe.proto.DataType.Long long = 7;</code>
   * @return Whether the long field is set.
   */
  boolean hasLong();
  /**
   * <code>.co.gaffe.proto.DataType.Long long = 7;</code>
   * @return The long.
   */
  co.gaffe.proto.DataType.Long getLong();

  /**
   * <code>.co.gaffe.proto.DataType.Float float = 8;</code>
   * @return Whether the float field is set.
   */
  boolean hasFloat();
  /**
   * <code>.co.gaffe.proto.DataType.Float float = 8;</code>
   * @return The float.
   */
  co.gaffe.proto.DataType.Float getFloat();

  /**
   * <code>.co.gaffe.proto.DataType.Double double = 9;</code>
   * @return Whether the double field is set.
   */
  boolean hasDouble();
  /**
   * <code>.co.gaffe.proto.DataType.Double double = 9;</code>
   * @return The double.
   */
  co.gaffe.proto.DataType.Double getDouble();

  /**
   * <code>.co.gaffe.proto.DataType.Decimal decimal = 10;</code>
   * @return Whether the decimal field is set.
   */
  boolean hasDecimal();
  /**
   * <code>.co.gaffe.proto.DataType.Decimal decimal = 10;</code>
   * @return The decimal.
   */
  co.gaffe.proto.DataType.Decimal getDecimal();

  /**
   * <pre>
   * String types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.String string = 11;</code>
   * @return Whether the string field is set.
   */
  boolean hasString();
  /**
   * <pre>
   * String types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.String string = 11;</code>
   * @return The string.
   */
  co.gaffe.proto.DataType.String getString();

  /**
   * <code>.co.gaffe.proto.DataType.Char char = 12;</code>
   * @return Whether the char field is set.
   */
  boolean hasChar();
  /**
   * <code>.co.gaffe.proto.DataType.Char char = 12;</code>
   * @return The char.
   */
  co.gaffe.proto.DataType.Char getChar();

  /**
   * <code>.co.gaffe.proto.DataType.VarChar var_char = 13;</code>
   * @return Whether the varChar field is set.
   */
  boolean hasVarChar();
  /**
   * <code>.co.gaffe.proto.DataType.VarChar var_char = 13;</code>
   * @return The varChar.
   */
  co.gaffe.proto.DataType.VarChar getVarChar();

  /**
   * <pre>
   * Datatime types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Date date = 14;</code>
   * @return Whether the date field is set.
   */
  boolean hasDate();
  /**
   * <pre>
   * Datatime types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Date date = 14;</code>
   * @return The date.
   */
  co.gaffe.proto.DataType.Date getDate();

  /**
   * <code>.co.gaffe.proto.DataType.Timestamp timestamp = 15;</code>
   * @return Whether the timestamp field is set.
   */
  boolean hasTimestamp();
  /**
   * <code>.co.gaffe.proto.DataType.Timestamp timestamp = 15;</code>
   * @return The timestamp.
   */
  co.gaffe.proto.DataType.Timestamp getTimestamp();

  /**
   * <code>.co.gaffe.proto.DataType.TimestampNTZ timestamp_ntz = 16;</code>
   * @return Whether the timestampNtz field is set.
   */
  boolean hasTimestampNtz();
  /**
   * <code>.co.gaffe.proto.DataType.TimestampNTZ timestamp_ntz = 16;</code>
   * @return The timestampNtz.
   */
  co.gaffe.proto.DataType.TimestampNTZ getTimestampNtz();

  /**
   * <pre>
   * Interval types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.CalendarInterval calendar_interval = 17;</code>
   * @return Whether the calendarInterval field is set.
   */
  boolean hasCalendarInterval();
  /**
   * <pre>
   * Interval types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.CalendarInterval calendar_interval = 17;</code>
   * @return The calendarInterval.
   */
  co.gaffe.proto.DataType.CalendarInterval getCalendarInterval();

  /**
   * <code>.co.gaffe.proto.DataType.YearMonthInterval year_month_interval = 18;</code>
   * @return Whether the yearMonthInterval field is set.
   */
  boolean hasYearMonthInterval();
  /**
   * <code>.co.gaffe.proto.DataType.YearMonthInterval year_month_interval = 18;</code>
   * @return The yearMonthInterval.
   */
  co.gaffe.proto.DataType.YearMonthInterval getYearMonthInterval();

  /**
   * <code>.co.gaffe.proto.DataType.DayTimeInterval day_time_interval = 19;</code>
   * @return Whether the dayTimeInterval field is set.
   */
  boolean hasDayTimeInterval();
  /**
   * <code>.co.gaffe.proto.DataType.DayTimeInterval day_time_interval = 19;</code>
   * @return The dayTimeInterval.
   */
  co.gaffe.proto.DataType.DayTimeInterval getDayTimeInterval();

  /**
   * <pre>
   * Complex types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Array array = 20;</code>
   * @return Whether the array field is set.
   */
  boolean hasArray();
  /**
   * <pre>
   * Complex types
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Array array = 20;</code>
   * @return The array.
   */
  co.gaffe.proto.DataType.Array getArray();

  /**
   * <code>.co.gaffe.proto.DataType.Struct struct = 21;</code>
   * @return Whether the struct field is set.
   */
  boolean hasStruct();
  /**
   * <code>.co.gaffe.proto.DataType.Struct struct = 21;</code>
   * @return The struct.
   */
  co.gaffe.proto.DataType.Struct getStruct();

  /**
   * <code>.co.gaffe.proto.DataType.Map map = 22;</code>
   * @return Whether the map field is set.
   */
  boolean hasMap();
  /**
   * <code>.co.gaffe.proto.DataType.Map map = 22;</code>
   * @return The map.
   */
  co.gaffe.proto.DataType.Map getMap();

  /**
   * <code>.co.gaffe.proto.DataType.Variant variant = 25;</code>
   * @return Whether the variant field is set.
   */
  boolean hasVariant();
  /**
   * <code>.co.gaffe.proto.DataType.Variant variant = 25;</code>
   * @return The variant.
   */
  co.gaffe.proto.DataType.Variant getVariant();

  /**
   * <pre>
   * UserDefinedType
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.UDT udt = 23;</code>
   * @return Whether the udt field is set.
   */
  boolean hasUdt();
  /**
   * <pre>
   * UserDefinedType
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.UDT udt = 23;</code>
   * @return The udt.
   */
  co.gaffe.proto.DataType.UDT getUdt();

  /**
   * <pre>
   * UnparsedDataType
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Unparsed unparsed = 24;</code>
   * @return Whether the unparsed field is set.
   */
  boolean hasUnparsed();
  /**
   * <pre>
   * UnparsedDataType
   * </pre>
   *
   * <code>.co.gaffe.proto.DataType.Unparsed unparsed = 24;</code>
   * @return The unparsed.
   */
  co.gaffe.proto.DataType.Unparsed getUnparsed();

  public co.gaffe.proto.DataType.KindCase getKindCase();
}
