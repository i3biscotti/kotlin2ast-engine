// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: statements.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializeifDefinitionStatement")
public inline fun ifDefinitionStatement(block: protocol.IfDefinitionStatementKt.Dsl.() -> kotlin.Unit): protocol.Statements.IfDefinitionStatement =
  protocol.IfDefinitionStatementKt.Dsl._create(protocol.Statements.IfDefinitionStatement.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.IfDefinitionStatement`
 */
public object IfDefinitionStatementKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Statements.IfDefinitionStatement.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Statements.IfDefinitionStatement.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Statements.IfDefinitionStatement = _builder.build()

    /**
     * `.protocol.IfBlock ifBlock = 1;`
     */
    public var ifBlock: protocol.Statements.IfBlock
      @JvmName("getIfBlock")
      get() = _builder.getIfBlock()
      @JvmName("setIfBlock")
      set(value) {
        _builder.setIfBlock(value)
      }
    /**
     * `.protocol.IfBlock ifBlock = 1;`
     */
    public fun clearIfBlock() {
      _builder.clearIfBlock()
    }
    /**
     * `.protocol.IfBlock ifBlock = 1;`
     * @return Whether the ifBlock field is set.
     */
    public fun hasIfBlock(): kotlin.Boolean {
      return _builder.hasIfBlock()
    }

    /**
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    public class ElseIfBlocksProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     */
     public val elseIfBlocks: com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>
      @kotlin.jvm.JvmSynthetic
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getElseIfBlocksList()
      )
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     * @param value The elseIfBlocks to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addElseIfBlocks")
    public fun com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>.add(value: protocol.Statements.IfBlock) {
      _builder.addElseIfBlocks(value)
    }
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     * @param value The elseIfBlocks to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignElseIfBlocks")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>.plusAssign(value: protocol.Statements.IfBlock) {
      add(value)
    }
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     * @param values The elseIfBlocks to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllElseIfBlocks")
    public fun com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>.addAll(values: kotlin.collections.Iterable<protocol.Statements.IfBlock>) {
      _builder.addAllElseIfBlocks(values)
    }
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     * @param values The elseIfBlocks to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllElseIfBlocks")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>.plusAssign(values: kotlin.collections.Iterable<protocol.Statements.IfBlock>) {
      addAll(values)
    }
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     * @param index The index to set the value at.
     * @param value The elseIfBlocks to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setElseIfBlocks")
    public operator fun com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>.set(index: kotlin.Int, value: protocol.Statements.IfBlock) {
      _builder.setElseIfBlocks(index, value)
    }
    /**
     * `repeated .protocol.IfBlock elseIfBlocks = 2;`
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearElseIfBlocks")
    public fun com.google.protobuf.kotlin.DslList<protocol.Statements.IfBlock, ElseIfBlocksProxy>.clear() {
      _builder.clearElseIfBlocks()
    }


    /**
     * `.protocol.IfBlock elseBlock = 3;`
     */
    public var elseBlock: protocol.Statements.IfBlock
      @JvmName("getElseBlock")
      get() = _builder.getElseBlock()
      @JvmName("setElseBlock")
      set(value) {
        _builder.setElseBlock(value)
      }
    /**
     * `.protocol.IfBlock elseBlock = 3;`
     */
    public fun clearElseBlock() {
      _builder.clearElseBlock()
    }
    /**
     * `.protocol.IfBlock elseBlock = 3;`
     * @return Whether the elseBlock field is set.
     */
    public fun hasElseBlock(): kotlin.Boolean {
      return _builder.hasElseBlock()
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
    public val IfDefinitionStatementKt.Dsl.positionOrNull: protocol.PositionOuterClass.Position?
      get() = _builder.positionOrNull
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Statements.IfDefinitionStatement.copy(block: `protocol`.IfDefinitionStatementKt.Dsl.() -> kotlin.Unit): protocol.Statements.IfDefinitionStatement =
  `protocol`.IfDefinitionStatementKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Statements.IfDefinitionStatementOrBuilder.ifBlockOrNull: protocol.Statements.IfBlock?
  get() = if (hasIfBlock()) getIfBlock() else null

public val protocol.Statements.IfDefinitionStatementOrBuilder.elseBlockOrNull: protocol.Statements.IfBlock?
  get() = if (hasElseBlock()) getElseBlock() else null

public val protocol.Statements.IfDefinitionStatementOrBuilder.positionOrNull: protocol.PositionOuterClass.Position?
  get() = if (hasPosition()) getPosition() else null

