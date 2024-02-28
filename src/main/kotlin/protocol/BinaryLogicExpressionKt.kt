// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializebinaryLogicExpression")
public inline fun binaryLogicExpression(block: protocol.BinaryLogicExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.BinaryLogicExpression =
  protocol.BinaryLogicExpressionKt.Dsl._create(protocol.Expressions.BinaryLogicExpression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.BinaryLogicExpression`
 */
public object BinaryLogicExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.BinaryLogicExpression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.BinaryLogicExpression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.BinaryLogicExpression = _builder.build()

    /**
     * `.protocol.Expression left = 1;`
     */
    public var left: protocol.Expressions.Expression
      @JvmName("getLeft")
      get() = _builder.getLeft()
      @JvmName("setLeft")
      set(value) {
        _builder.setLeft(value)
      }
    /**
     * `.protocol.Expression left = 1;`
     */
    public fun clearLeft() {
      _builder.clearLeft()
    }
    /**
     * `.protocol.Expression left = 1;`
     * @return Whether the left field is set.
     */
    public fun hasLeft(): kotlin.Boolean {
      return _builder.hasLeft()
    }

    /**
     * `.protocol.Expression right = 2;`
     */
    public var right: protocol.Expressions.Expression
      @JvmName("getRight")
      get() = _builder.getRight()
      @JvmName("setRight")
      set(value) {
        _builder.setRight(value)
      }
    /**
     * `.protocol.Expression right = 2;`
     */
    public fun clearRight() {
      _builder.clearRight()
    }
    /**
     * `.protocol.Expression right = 2;`
     * @return Whether the right field is set.
     */
    public fun hasRight(): kotlin.Boolean {
      return _builder.hasRight()
    }

    /**
     * `.protocol.LogicOperand operand = 3;`
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
     * `.protocol.LogicOperand operand = 3;`
     */
    public fun clearOperand() {
      _builder.clearOperand()
    }

    /**
     * `.protocol.Position position = 4;`
     */
    public var position: protocol.PositionOuterClass.Position
      @JvmName("getPosition")
      get() = _builder.getPosition()
      @JvmName("setPosition")
      set(value) {
        _builder.setPosition(value)
      }
    /**
     * `.protocol.Position position = 4;`
     */
    public fun clearPosition() {
      _builder.clearPosition()
    }
    /**
     * `.protocol.Position position = 4;`
     * @return Whether the position field is set.
     */
    public fun hasPosition(): kotlin.Boolean {
      return _builder.hasPosition()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.BinaryLogicExpression.copy(block: `protocol`.BinaryLogicExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.BinaryLogicExpression =
  `protocol`.BinaryLogicExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.BinaryLogicExpressionOrBuilder.leftOrNull: protocol.Expressions.Expression?
  get() = if (hasLeft()) getLeft() else null

public val protocol.Expressions.BinaryLogicExpressionOrBuilder.rightOrNull: protocol.Expressions.Expression?
  get() = if (hasRight()) getRight() else null

public val protocol.Expressions.BinaryLogicExpressionOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

