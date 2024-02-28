// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializefunctionCallExpression")
public inline fun functionCallExpression(block: protocol.FunctionCallExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.FunctionCallExpression =
  protocol.FunctionCallExpressionKt.Dsl._create(protocol.Expressions.FunctionCallExpression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.FunctionCallExpression`
 */
public object FunctionCallExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.FunctionCallExpression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.FunctionCallExpression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.FunctionCallExpression = _builder.build()

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
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    public class ParametersProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * `repeated .protocol.Expression parameters = 2;`
     */
     public val parameters: com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>
      @kotlin.jvm.JvmSynthetic
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getParametersList()
      )
    /**
     * `repeated .protocol.Expression parameters = 2;`
     * @param value The parameters to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addParameters")
    public fun com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>.add(value: protocol.Expressions.Expression) {
      _builder.addParameters(value)
    }
    /**
     * `repeated .protocol.Expression parameters = 2;`
     * @param value The parameters to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignParameters")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>.plusAssign(value: protocol.Expressions.Expression) {
      add(value)
    }
    /**
     * `repeated .protocol.Expression parameters = 2;`
     * @param values The parameters to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllParameters")
    public fun com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>.addAll(values: kotlin.collections.Iterable<protocol.Expressions.Expression>) {
      _builder.addAllParameters(values)
    }
    /**
     * `repeated .protocol.Expression parameters = 2;`
     * @param values The parameters to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllParameters")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>.plusAssign(values: kotlin.collections.Iterable<protocol.Expressions.Expression>) {
      addAll(values)
    }
    /**
     * `repeated .protocol.Expression parameters = 2;`
     * @param index The index to set the value at.
     * @param value The parameters to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setParameters")
    public operator fun com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>.set(index: kotlin.Int, value: protocol.Expressions.Expression) {
      _builder.setParameters(index, value)
    }
    /**
     * `repeated .protocol.Expression parameters = 2;`
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearParameters")
    public fun com.google.protobuf.kotlin.DslList<protocol.Expressions.Expression, ParametersProxy>.clear() {
      _builder.clearParameters()
    }


    /**
     * `.protocol.Position position = 3;`
     */
    public var position: protocol.PositionOuterClass.Position
      @JvmName("getPosition")
      get() = _builder.getPosition()
      @JvmName("setPosition")
      set(value) {
        _builder.setPosition(value)
      }
    /**
     * `.protocol.Position position = 3;`
     */
    public fun clearPosition() {
      _builder.clearPosition()
    }
    /**
     * `.protocol.Position position = 3;`
     * @return Whether the position field is set.
     */
    public fun hasPosition(): kotlin.Boolean {
      return _builder.hasPosition()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.FunctionCallExpression.copy(block: `protocol`.FunctionCallExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.FunctionCallExpression =
  `protocol`.FunctionCallExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.FunctionCallExpressionOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

