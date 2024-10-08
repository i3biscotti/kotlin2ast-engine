// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializebinaryExpression")
public inline fun binaryExpression(block: protocol.BinaryExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.BinaryExpression =
  protocol.BinaryExpressionKt.Dsl._create(protocol.Expressions.BinaryExpression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.BinaryExpression`
 */
public object BinaryExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.BinaryExpression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.BinaryExpression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.BinaryExpression = _builder.build()

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
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.BinaryExpression.copy(block: `protocol`.BinaryExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.BinaryExpression =
  `protocol`.BinaryExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.BinaryExpressionOrBuilder.leftOrNull: protocol.Expressions.Expression?
  get() = if (hasLeft()) getLeft() else null

public val protocol.Expressions.BinaryExpressionOrBuilder.rightOrNull: protocol.Expressions.Expression?
  get() = if (hasRight()) getRight() else null

