// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializelistLiteralExpression")
public inline fun listLiteralExpression(block: protocol.ListLiteralExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.ListLiteralExpression =
  protocol.ListLiteralExpressionKt.Dsl._create(protocol.Expressions.ListLiteralExpression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.ListLiteralExpression`
 */
public object ListLiteralExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.ListLiteralExpression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.ListLiteralExpression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.ListLiteralExpression = _builder.build()

    /**
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    public class ValueProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * `repeated .google.protobuf.Any value = 1;`
     */
     public val value: com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>
      @kotlin.jvm.JvmSynthetic
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getValueList()
      )
    /**
     * `repeated .google.protobuf.Any value = 1;`
     * @param value The value to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addValue")
    public fun com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>.add(value: com.google.protobuf.Any) {
      _builder.addValue(value)
    }
    /**
     * `repeated .google.protobuf.Any value = 1;`
     * @param value The value to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignValue")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>.plusAssign(value: com.google.protobuf.Any) {
      add(value)
    }
    /**
     * `repeated .google.protobuf.Any value = 1;`
     * @param values The value to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllValue")
    public fun com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>.addAll(values: kotlin.collections.Iterable<com.google.protobuf.Any>) {
      _builder.addAllValue(values)
    }
    /**
     * `repeated .google.protobuf.Any value = 1;`
     * @param values The value to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllValue")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>.plusAssign(values: kotlin.collections.Iterable<com.google.protobuf.Any>) {
      addAll(values)
    }
    /**
     * `repeated .google.protobuf.Any value = 1;`
     * @param index The index to set the value at.
     * @param value The value to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setValue")
    public operator fun com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>.set(index: kotlin.Int, value: com.google.protobuf.Any) {
      _builder.setValue(index, value)
    }
    /**
     * `repeated .google.protobuf.Any value = 1;`
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearValue")
    public fun com.google.protobuf.kotlin.DslList<com.google.protobuf.Any, ValueProxy>.clear() {
      _builder.clearValue()
    }


    /**
     * `.protocol.Position position = 2;`
     */
    public var position: protocol.PositionOuterClass.Position
      @JvmName("getPosition")
      get() = _builder.getPosition()
      @JvmName("setPosition")
      set(value) {
        _builder.setPosition(value)
      }
    /**
     * `.protocol.Position position = 2;`
     */
    public fun clearPosition() {
      _builder.clearPosition()
    }
    /**
     * `.protocol.Position position = 2;`
     * @return Whether the position field is set.
     */
    public fun hasPosition(): kotlin.Boolean {
      return _builder.hasPosition()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.ListLiteralExpression.copy(block: `protocol`.ListLiteralExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.ListLiteralExpression =
  `protocol`.ListLiteralExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.ListLiteralExpressionOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

