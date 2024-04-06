// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: expressions.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializeexpression")
public inline fun expression(block: protocol.ExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.Expression =
  protocol.ExpressionKt.Dsl._create(protocol.Expressions.Expression.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.Expression`
 */
public object ExpressionKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Expressions.Expression.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Expressions.Expression.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Expressions.Expression = _builder.build()

    /**
     * `.protocol.IntLit intLit = 2;`
     */
    public var intLit: protocol.Expressions.IntLit
      @JvmName("getIntLit")
      get() = _builder.getIntLit()
      @JvmName("setIntLit")
      set(value) {
        _builder.setIntLit(value)
      }
    /**
     * `.protocol.IntLit intLit = 2;`
     */
    public fun clearIntLit() {
      _builder.clearIntLit()
    }
    /**
     * `.protocol.IntLit intLit = 2;`
     * @return Whether the intLit field is set.
     */
    public fun hasIntLit(): kotlin.Boolean {
      return _builder.hasIntLit()
    }

    /**
     * `.protocol.DecLit decLit = 3;`
     */
    public var decLit: protocol.Expressions.DecLit
      @JvmName("getDecLit")
      get() = _builder.getDecLit()
      @JvmName("setDecLit")
      set(value) {
        _builder.setDecLit(value)
      }
    /**
     * `.protocol.DecLit decLit = 3;`
     */
    public fun clearDecLit() {
      _builder.clearDecLit()
    }
    /**
     * `.protocol.DecLit decLit = 3;`
     * @return Whether the decLit field is set.
     */
    public fun hasDecLit(): kotlin.Boolean {
      return _builder.hasDecLit()
    }

    /**
     * `.protocol.StringLit stringLit = 4;`
     */
    public var stringLit: protocol.Expressions.StringLit
      @JvmName("getStringLit")
      get() = _builder.getStringLit()
      @JvmName("setStringLit")
      set(value) {
        _builder.setStringLit(value)
      }
    /**
     * `.protocol.StringLit stringLit = 4;`
     */
    public fun clearStringLit() {
      _builder.clearStringLit()
    }
    /**
     * `.protocol.StringLit stringLit = 4;`
     * @return Whether the stringLit field is set.
     */
    public fun hasStringLit(): kotlin.Boolean {
      return _builder.hasStringLit()
    }

    /**
     * `.protocol.BoolLit boolLit = 5;`
     */
    public var boolLit: protocol.Expressions.BoolLit
      @JvmName("getBoolLit")
      get() = _builder.getBoolLit()
      @JvmName("setBoolLit")
      set(value) {
        _builder.setBoolLit(value)
      }
    /**
     * `.protocol.BoolLit boolLit = 5;`
     */
    public fun clearBoolLit() {
      _builder.clearBoolLit()
    }
    /**
     * `.protocol.BoolLit boolLit = 5;`
     * @return Whether the boolLit field is set.
     */
    public fun hasBoolLit(): kotlin.Boolean {
      return _builder.hasBoolLit()
    }

    /**
     * `.protocol.ListLiteralExpression listLiteralExpression = 6;`
     */
    public var listLiteralExpression: protocol.Expressions.ListLiteralExpression
      @JvmName("getListLiteralExpression")
      get() = _builder.getListLiteralExpression()
      @JvmName("setListLiteralExpression")
      set(value) {
        _builder.setListLiteralExpression(value)
      }
    /**
     * `.protocol.ListLiteralExpression listLiteralExpression = 6;`
     */
    public fun clearListLiteralExpression() {
      _builder.clearListLiteralExpression()
    }
    /**
     * `.protocol.ListLiteralExpression listLiteralExpression = 6;`
     * @return Whether the listLiteralExpression field is set.
     */
    public fun hasListLiteralExpression(): kotlin.Boolean {
      return _builder.hasListLiteralExpression()
    }

    /**
     * `.protocol.BinaryMathExpression binaryMathExpression = 7;`
     */
    public var binaryMathExpression: protocol.Expressions.BinaryMathExpression
      @JvmName("getBinaryMathExpression")
      get() = _builder.getBinaryMathExpression()
      @JvmName("setBinaryMathExpression")
      set(value) {
        _builder.setBinaryMathExpression(value)
      }
    /**
     * `.protocol.BinaryMathExpression binaryMathExpression = 7;`
     */
    public fun clearBinaryMathExpression() {
      _builder.clearBinaryMathExpression()
    }
    /**
     * `.protocol.BinaryMathExpression binaryMathExpression = 7;`
     * @return Whether the binaryMathExpression field is set.
     */
    public fun hasBinaryMathExpression(): kotlin.Boolean {
      return _builder.hasBinaryMathExpression()
    }

    /**
     * `.protocol.BinaryLogicExpression binaryLogicExpression = 8;`
     */
    public var binaryLogicExpression: protocol.Expressions.BinaryLogicExpression
      @JvmName("getBinaryLogicExpression")
      get() = _builder.getBinaryLogicExpression()
      @JvmName("setBinaryLogicExpression")
      set(value) {
        _builder.setBinaryLogicExpression(value)
      }
    /**
     * `.protocol.BinaryLogicExpression binaryLogicExpression = 8;`
     */
    public fun clearBinaryLogicExpression() {
      _builder.clearBinaryLogicExpression()
    }
    /**
     * `.protocol.BinaryLogicExpression binaryLogicExpression = 8;`
     * @return Whether the binaryLogicExpression field is set.
     */
    public fun hasBinaryLogicExpression(): kotlin.Boolean {
      return _builder.hasBinaryLogicExpression()
    }

    /**
     * `.protocol.UnaryMathExpression unaryMathExpression = 9;`
     */
    public var unaryMathExpression: protocol.Expressions.UnaryMathExpression
      @JvmName("getUnaryMathExpression")
      get() = _builder.getUnaryMathExpression()
      @JvmName("setUnaryMathExpression")
      set(value) {
        _builder.setUnaryMathExpression(value)
      }
    /**
     * `.protocol.UnaryMathExpression unaryMathExpression = 9;`
     */
    public fun clearUnaryMathExpression() {
      _builder.clearUnaryMathExpression()
    }
    /**
     * `.protocol.UnaryMathExpression unaryMathExpression = 9;`
     * @return Whether the unaryMathExpression field is set.
     */
    public fun hasUnaryMathExpression(): kotlin.Boolean {
      return _builder.hasUnaryMathExpression()
    }

    /**
     * `.protocol.UnaryLogicExpression unaryLogicExpression = 10;`
     */
    public var unaryLogicExpression: protocol.Expressions.UnaryLogicExpression
      @JvmName("getUnaryLogicExpression")
      get() = _builder.getUnaryLogicExpression()
      @JvmName("setUnaryLogicExpression")
      set(value) {
        _builder.setUnaryLogicExpression(value)
      }
    /**
     * `.protocol.UnaryLogicExpression unaryLogicExpression = 10;`
     */
    public fun clearUnaryLogicExpression() {
      _builder.clearUnaryLogicExpression()
    }
    /**
     * `.protocol.UnaryLogicExpression unaryLogicExpression = 10;`
     * @return Whether the unaryLogicExpression field is set.
     */
    public fun hasUnaryLogicExpression(): kotlin.Boolean {
      return _builder.hasUnaryLogicExpression()
    }

    /**
     * `.protocol.PreIncrementExpression preIncrementExpression = 11;`
     */
    public var preIncrementExpression: protocol.Expressions.PreIncrementExpression
      @JvmName("getPreIncrementExpression")
      get() = _builder.getPreIncrementExpression()
      @JvmName("setPreIncrementExpression")
      set(value) {
        _builder.setPreIncrementExpression(value)
      }
    /**
     * `.protocol.PreIncrementExpression preIncrementExpression = 11;`
     */
    public fun clearPreIncrementExpression() {
      _builder.clearPreIncrementExpression()
    }
    /**
     * `.protocol.PreIncrementExpression preIncrementExpression = 11;`
     * @return Whether the preIncrementExpression field is set.
     */
    public fun hasPreIncrementExpression(): kotlin.Boolean {
      return _builder.hasPreIncrementExpression()
    }

    /**
     * `.protocol.PostIncrementExpression postIncrementExpression = 12;`
     */
    public var postIncrementExpression: protocol.Expressions.PostIncrementExpression
      @JvmName("getPostIncrementExpression")
      get() = _builder.getPostIncrementExpression()
      @JvmName("setPostIncrementExpression")
      set(value) {
        _builder.setPostIncrementExpression(value)
      }
    /**
     * `.protocol.PostIncrementExpression postIncrementExpression = 12;`
     */
    public fun clearPostIncrementExpression() {
      _builder.clearPostIncrementExpression()
    }
    /**
     * `.protocol.PostIncrementExpression postIncrementExpression = 12;`
     * @return Whether the postIncrementExpression field is set.
     */
    public fun hasPostIncrementExpression(): kotlin.Boolean {
      return _builder.hasPostIncrementExpression()
    }

    /**
     * `.protocol.PreDecrementExpression preDecrementExpression = 13;`
     */
    public var preDecrementExpression: protocol.Expressions.PreDecrementExpression
      @JvmName("getPreDecrementExpression")
      get() = _builder.getPreDecrementExpression()
      @JvmName("setPreDecrementExpression")
      set(value) {
        _builder.setPreDecrementExpression(value)
      }
    /**
     * `.protocol.PreDecrementExpression preDecrementExpression = 13;`
     */
    public fun clearPreDecrementExpression() {
      _builder.clearPreDecrementExpression()
    }
    /**
     * `.protocol.PreDecrementExpression preDecrementExpression = 13;`
     * @return Whether the preDecrementExpression field is set.
     */
    public fun hasPreDecrementExpression(): kotlin.Boolean {
      return _builder.hasPreDecrementExpression()
    }

    /**
     * `.protocol.PostDecrementExpression postDecrementExpression = 14;`
     */
    public var postDecrementExpression: protocol.Expressions.PostDecrementExpression
      @JvmName("getPostDecrementExpression")
      get() = _builder.getPostDecrementExpression()
      @JvmName("setPostDecrementExpression")
      set(value) {
        _builder.setPostDecrementExpression(value)
      }
    /**
     * `.protocol.PostDecrementExpression postDecrementExpression = 14;`
     */
    public fun clearPostDecrementExpression() {
      _builder.clearPostDecrementExpression()
    }
    /**
     * `.protocol.PostDecrementExpression postDecrementExpression = 14;`
     * @return Whether the postDecrementExpression field is set.
     */
    public fun hasPostDecrementExpression(): kotlin.Boolean {
      return _builder.hasPostDecrementExpression()
    }

    /**
     * `.protocol.InputExpression inputExpression = 15;`
     */
    public var inputExpression: protocol.Expressions.InputExpression
      @JvmName("getInputExpression")
      get() = _builder.getInputExpression()
      @JvmName("setInputExpression")
      set(value) {
        _builder.setInputExpression(value)
      }
    /**
     * `.protocol.InputExpression inputExpression = 15;`
     */
    public fun clearInputExpression() {
      _builder.clearInputExpression()
    }
    /**
     * `.protocol.InputExpression inputExpression = 15;`
     * @return Whether the inputExpression field is set.
     */
    public fun hasInputExpression(): kotlin.Boolean {
      return _builder.hasInputExpression()
    }

    /**
     * `.protocol.OutputExpression outputExpression = 16;`
     */
    public var outputExpression: protocol.Expressions.OutputExpression
      @JvmName("getOutputExpression")
      get() = _builder.getOutputExpression()
      @JvmName("setOutputExpression")
      set(value) {
        _builder.setOutputExpression(value)
      }
    /**
     * `.protocol.OutputExpression outputExpression = 16;`
     */
    public fun clearOutputExpression() {
      _builder.clearOutputExpression()
    }
    /**
     * `.protocol.OutputExpression outputExpression = 16;`
     * @return Whether the outputExpression field is set.
     */
    public fun hasOutputExpression(): kotlin.Boolean {
      return _builder.hasOutputExpression()
    }

    /**
     * `.protocol.VarReferenceExpression varReferenceExpression = 17;`
     */
    public var varReferenceExpression: protocol.Expressions.VarReferenceExpression
      @JvmName("getVarReferenceExpression")
      get() = _builder.getVarReferenceExpression()
      @JvmName("setVarReferenceExpression")
      set(value) {
        _builder.setVarReferenceExpression(value)
      }
    /**
     * `.protocol.VarReferenceExpression varReferenceExpression = 17;`
     */
    public fun clearVarReferenceExpression() {
      _builder.clearVarReferenceExpression()
    }
    /**
     * `.protocol.VarReferenceExpression varReferenceExpression = 17;`
     * @return Whether the varReferenceExpression field is set.
     */
    public fun hasVarReferenceExpression(): kotlin.Boolean {
      return _builder.hasVarReferenceExpression()
    }

    /**
     * `.protocol.ParenthesisExpression parenthesysExpression = 18;`
     */
    public var parenthesysExpression: protocol.Expressions.ParenthesisExpression
      @JvmName("getParenthesysExpression")
      get() = _builder.getParenthesysExpression()
      @JvmName("setParenthesysExpression")
      set(value) {
        _builder.setParenthesysExpression(value)
      }
    /**
     * `.protocol.ParenthesisExpression parenthesysExpression = 18;`
     */
    public fun clearParenthesysExpression() {
      _builder.clearParenthesysExpression()
    }
    /**
     * `.protocol.ParenthesisExpression parenthesysExpression = 18;`
     * @return Whether the parenthesysExpression field is set.
     */
    public fun hasParenthesysExpression(): kotlin.Boolean {
      return _builder.hasParenthesysExpression()
    }

    /**
     * `.protocol.FunctionCallExpression functionCallExpression = 19;`
     */
    public var functionCallExpression: protocol.Expressions.FunctionCallExpression
      @JvmName("getFunctionCallExpression")
      get() = _builder.getFunctionCallExpression()
      @JvmName("setFunctionCallExpression")
      set(value) {
        _builder.setFunctionCallExpression(value)
      }
    /**
     * `.protocol.FunctionCallExpression functionCallExpression = 19;`
     */
    public fun clearFunctionCallExpression() {
      _builder.clearFunctionCallExpression()
    }
    /**
     * `.protocol.FunctionCallExpression functionCallExpression = 19;`
     * @return Whether the functionCallExpression field is set.
     */
    public fun hasFunctionCallExpression(): kotlin.Boolean {
      return _builder.hasFunctionCallExpression()
    }

    /**
     * `.protocol.ObjectPropertyReferenceExpression objectPropertyReferenceExpression = 20;`
     */
    public var objectPropertyReferenceExpression: protocol.Expressions.ObjectPropertyReferenceExpression
      @JvmName("getObjectPropertyReferenceExpression")
      get() = _builder.getObjectPropertyReferenceExpression()
      @JvmName("setObjectPropertyReferenceExpression")
      set(value) {
        _builder.setObjectPropertyReferenceExpression(value)
      }
    /**
     * `.protocol.ObjectPropertyReferenceExpression objectPropertyReferenceExpression = 20;`
     */
    public fun clearObjectPropertyReferenceExpression() {
      _builder.clearObjectPropertyReferenceExpression()
    }
    /**
     * `.protocol.ObjectPropertyReferenceExpression objectPropertyReferenceExpression = 20;`
     * @return Whether the objectPropertyReferenceExpression field is set.
     */
    public fun hasObjectPropertyReferenceExpression(): kotlin.Boolean {
      return _builder.hasObjectPropertyReferenceExpression()
    }

    /**
     * `.protocol.ObjectMethodCallExpression objectMethodCallExpression = 21;`
     */
    public var objectMethodCallExpression: protocol.Expressions.ObjectMethodCallExpression
      @JvmName("getObjectMethodCallExpression")
      get() = _builder.getObjectMethodCallExpression()
      @JvmName("setObjectMethodCallExpression")
      set(value) {
        _builder.setObjectMethodCallExpression(value)
      }
    /**
     * `.protocol.ObjectMethodCallExpression objectMethodCallExpression = 21;`
     */
    public fun clearObjectMethodCallExpression() {
      _builder.clearObjectMethodCallExpression()
    }
    /**
     * `.protocol.ObjectMethodCallExpression objectMethodCallExpression = 21;`
     * @return Whether the objectMethodCallExpression field is set.
     */
    public fun hasObjectMethodCallExpression(): kotlin.Boolean {
      return _builder.hasObjectMethodCallExpression()
    }
    public val exprCase: protocol.Expressions.Expression.ExprCase
      @JvmName("getExprCase")
      get() = _builder.getExprCase()

    public fun clearExpr() {
      _builder.clearExpr()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Expressions.Expression.copy(block: `protocol`.ExpressionKt.Dsl.() -> kotlin.Unit): protocol.Expressions.Expression =
  `protocol`.ExpressionKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val protocol.Expressions.ExpressionOrBuilder.intLitOrNull: protocol.Expressions.IntLit?
  get() = if (hasIntLit()) getIntLit() else null

public val protocol.Expressions.ExpressionOrBuilder.decLitOrNull: protocol.Expressions.DecLit?
  get() = if (hasDecLit()) getDecLit() else null

public val protocol.Expressions.ExpressionOrBuilder.stringLitOrNull: protocol.Expressions.StringLit?
  get() = if (hasStringLit()) getStringLit() else null

public val protocol.Expressions.ExpressionOrBuilder.boolLitOrNull: protocol.Expressions.BoolLit?
  get() = if (hasBoolLit()) getBoolLit() else null

public val protocol.Expressions.ExpressionOrBuilder.listLiteralExpressionOrNull: protocol.Expressions.ListLiteralExpression?
  get() = if (hasListLiteralExpression()) getListLiteralExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.binaryMathExpressionOrNull: protocol.Expressions.BinaryMathExpression?
  get() = if (hasBinaryMathExpression()) getBinaryMathExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.binaryLogicExpressionOrNull: protocol.Expressions.BinaryLogicExpression?
  get() = if (hasBinaryLogicExpression()) getBinaryLogicExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.unaryMathExpressionOrNull: protocol.Expressions.UnaryMathExpression?
  get() = if (hasUnaryMathExpression()) getUnaryMathExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.unaryLogicExpressionOrNull: protocol.Expressions.UnaryLogicExpression?
  get() = if (hasUnaryLogicExpression()) getUnaryLogicExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.preIncrementExpressionOrNull: protocol.Expressions.PreIncrementExpression?
  get() = if (hasPreIncrementExpression()) getPreIncrementExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.postIncrementExpressionOrNull: protocol.Expressions.PostIncrementExpression?
  get() = if (hasPostIncrementExpression()) getPostIncrementExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.preDecrementExpressionOrNull: protocol.Expressions.PreDecrementExpression?
  get() = if (hasPreDecrementExpression()) getPreDecrementExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.postDecrementExpressionOrNull: protocol.Expressions.PostDecrementExpression?
  get() = if (hasPostDecrementExpression()) getPostDecrementExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.inputExpressionOrNull: protocol.Expressions.InputExpression?
  get() = if (hasInputExpression()) getInputExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.outputExpressionOrNull: protocol.Expressions.OutputExpression?
  get() = if (hasOutputExpression()) getOutputExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.varReferenceExpressionOrNull: protocol.Expressions.VarReferenceExpression?
  get() = if (hasVarReferenceExpression()) getVarReferenceExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.parenthesysExpressionOrNull: protocol.Expressions.ParenthesisExpression?
  get() = if (hasParenthesysExpression()) getParenthesysExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.functionCallExpressionOrNull: protocol.Expressions.FunctionCallExpression?
  get() = if (hasFunctionCallExpression()) getFunctionCallExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.objectPropertyReferenceExpressionOrNull: protocol.Expressions.ObjectPropertyReferenceExpression?
  get() = if (hasObjectPropertyReferenceExpression()) getObjectPropertyReferenceExpression() else null

public val protocol.Expressions.ExpressionOrBuilder.objectMethodCallExpressionOrNull: protocol.Expressions.ObjectMethodCallExpression?
  get() = if (hasObjectMethodCallExpression()) getObjectMethodCallExpression() else null

