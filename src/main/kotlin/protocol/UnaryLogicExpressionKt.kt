// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializeunaryLogicExpression")
public inline fun unaryLogicExpression(block: protocol.UnaryLogicExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.UnaryLogicExpression =
  protocol.UnaryLogicExpressionKt.Dsl._create(protocol.Expressions.UnaryLogicExpression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.UnaryLogicExpression`
 */
public object UnaryLogicExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.UnaryLogicExpression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.UnaryLogicExpression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.UnaryLogicExpression = _builder.build()

    /**
     * `.protocol.Expression value = 1;`
     */
    public var value: protocol.Expressions.Expression
      @JvmName("getValue")
      get() = _builder.getValue()
      @JvmName("setValue")
      set(value) {
        _builder.setValue(value)
      }
    /**
     * `.protocol.Expression value = 1;`
     */
    public fun clearValue() {
      _builder.clearValue()
    }
    /**
     * `.protocol.Expression value = 1;`
     * @return Whether the value field is set.
     */
    public fun hasValue(): kotlin.Boolean {
      return _builder.hasValue()
    }

    /**
     * `.protocol.LogicOperand operand = 2;`
     */
    public var operand: protocol.Expressions.LogicOperand
      @JvmName("getOperand")
      get() = _builder.getOperand()
      @JvmName("setOperand")
      set(value) {
        _builder.setOperand(value)
      }
    public var operandValue: kotlin.Int
      @JvmName("getOperandValue")
      get() = _builder.getOperandValue()
      @JvmName("setOperandValue")
      set(value) {
        _builder.setOperandValue(value)
      }
    /**
     * `.protocol.LogicOperand operand = 2;`
     */
    public fun clearOperand() {
      _builder.clearOperand()
    }

    /**
     * `optional .protocol.Position position = 4;`
     */
    public var position: protocol.PositionOuterClass.Position
      @JvmName("getPosition")
      get() = _builder.getPosition()
      @JvmName("setPosition")
      set(value) {
        _builder.setPosition(value)
      }
    /**
     * `optional .protocol.Position position = 4;`
     */
    public fun clearPosition() {
      _builder.clearPosition()
    }
    /**
     * `optional .protocol.Position position = 4;`
     * @return Whether the position field is set.
     */
    public fun hasPosition(): kotlin.Boolean {
      return _builder.hasPosition()
    }
    public val UnaryLogicExpressionKt.Dsl.positionOrNull: protocol.PositionOuterClass.Position?
      get() = _builder.positionOrNull
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.UnaryLogicExpression.copy(block: `protocol`.UnaryLogicExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.UnaryLogicExpression =
  `protocol`.UnaryLogicExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.UnaryLogicExpressionOrBuilder.valueOrNull: protocol.Expressions.Expression?
  get() = if (hasValue()) getValue() else null

public val protocol.Expressions.UnaryLogicExpressionOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

