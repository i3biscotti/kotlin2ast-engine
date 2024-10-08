// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: statements.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializeassignmentForStatement")
public inline fun assignmentForStatement(block: protocol.AssignmentForStatementKt.Dsl.() -> kotlin.Unit): protocol.Statements.AssignmentForStatement =
  protocol.AssignmentForStatementKt.Dsl._create(protocol.Statements.AssignmentForStatement.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.AssignmentForStatement`
 */
public object AssignmentForStatementKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Statements.AssignmentForStatement.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Statements.AssignmentForStatement.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Statements.AssignmentForStatement = _builder.build()

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
     * `.protocol.Expression value = 2;`
     */
    public var value: protocol.Expressions.Expression
      @JvmName("getValue")
      get() = _builder.getValue()
      @JvmName("setValue")
      set(value) {
        _builder.setValue(value)
      }
    /**
     * `.protocol.Expression value = 2;`
     */
    public fun clearValue() {
      _builder.clearValue()
    }
    /**
     * `.protocol.Expression value = 2;`
     * @return Whether the value field is set.
     */
    public fun hasValue(): kotlin.Boolean {
      return _builder.hasValue()
    }

    /**
     * `optional .protocol.Position position = 3;`
     */
    public var position: protocol.PositionOuterClass.Position
      @JvmName("getPosition")
      get() = _builder.getPosition()
      @JvmName("setPosition")
      set(value) {
        _builder.setPosition(value)
      }
    /**
     * `optional .protocol.Position position = 3;`
     */
    public fun clearPosition() {
      _builder.clearPosition()
    }
    /**
     * `optional .protocol.Position position = 3;`
     * @return Whether the position field is set.
     */
    public fun hasPosition(): kotlin.Boolean {
      return _builder.hasPosition()
    }
    public val AssignmentForStatementKt.Dsl.positionOrNull: protocol.PositionOuterClass.Position?
      get() = _builder.positionOrNull
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Statements.AssignmentForStatement.copy(block: `protocol`.AssignmentForStatementKt.Dsl.() -> kotlin.Unit): protocol.Statements.AssignmentForStatement =
  `protocol`.AssignmentForStatementKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Statements.AssignmentForStatementOrBuilder.valueOrNull: protocol.Expressions.Expression?
  get() = if (hasValue()) getValue() else null

public val protocol.Statements.AssignmentForStatementOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

