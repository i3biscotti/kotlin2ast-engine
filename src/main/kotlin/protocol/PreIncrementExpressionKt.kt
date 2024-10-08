// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializepreIncrementExpression")
public inline fun preIncrementExpression(block: protocol.PreIncrementExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.PreIncrementExpression =
  protocol.PreIncrementExpressionKt.Dsl._create(protocol.Expressions.PreIncrementExpression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.PreIncrementExpression`
 */
public object PreIncrementExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.PreIncrementExpression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.PreIncrementExpression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.PreIncrementExpression = _builder.build()

    /**
     * `string name = 1;`
     */
    public var name: kotlin.String
      @JvmName("getName")
      get() = _builder.getName()
      @JvmName("setName")
      set(value) {
        _builder.setName(value)
      }
    /**
     * `string name = 1;`
     */
    public fun clearName() {
      _builder.clearName()
    }

    /**
     * `optional .protocol.Position position = 2;`
     */
    public var position: protocol.PositionOuterClass.Position
      @JvmName("getPosition")
      get() = _builder.getPosition()
      @JvmName("setPosition")
      set(value) {
        _builder.setPosition(value)
      }
    /**
     * `optional .protocol.Position position = 2;`
     */
    public fun clearPosition() {
      _builder.clearPosition()
    }
    /**
     * `optional .protocol.Position position = 2;`
     * @return Whether the position field is set.
     */
    public fun hasPosition(): kotlin.Boolean {
      return _builder.hasPosition()
    }
    public val PreIncrementExpressionKt.Dsl.positionOrNull: protocol.PositionOuterClass.Position?
      get() = _builder.positionOrNull
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.PreIncrementExpression.copy(block: `protocol`.PreIncrementExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.PreIncrementExpression =
  `protocol`.PreIncrementExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.PreIncrementExpressionOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

