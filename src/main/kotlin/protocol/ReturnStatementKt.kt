// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: statements.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializereturnStatement")
public inline fun returnStatement(block: protocol.ReturnStatementKt.Dsl.() -> kotlin.Unit): protocol.Statements.ReturnStatement =
  protocol.ReturnStatementKt.Dsl._create(protocol.Statements.ReturnStatement.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.ReturnStatement`
 */
public object ReturnStatementKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Statements.ReturnStatement.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Statements.ReturnStatement.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Statements.ReturnStatement = _builder.build()

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
    public val ReturnStatementKt.Dsl.positionOrNull: protocol.PositionOuterClass.Position?
      get() = _builder.positionOrNull
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Statements.ReturnStatement.copy(block: `protocol`.ReturnStatementKt.Dsl.() -> kotlin.Unit): protocol.Statements.ReturnStatement =
  `protocol`.ReturnStatementKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Statements.ReturnStatementOrBuilder.valueOrNull: protocol.Expressions.Expression?
  get() = if (hasValue()) getValue() else null

public val protocol.Statements.ReturnStatementOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

