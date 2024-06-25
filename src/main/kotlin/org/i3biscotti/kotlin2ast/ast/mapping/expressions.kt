package org.i3biscotti.kotlin2ast.ast.mapping

import KotlinParser.*
import org.i3biscotti.kotlin2ast.ast.models.*

fun ExpressionContext.toAst(considerPosition: Boolean): Expression {
    return when (this) {
        is BoolLiteralExpressionContext -> BooleanLitExpression(text, toPosition(considerPosition))
        is IntLiteralExpressionContext -> IntLiteralExpression(text, toPosition(considerPosition))
        is DoubleLiteralExpressionContext -> DoubleLiteralExpression(text, toPosition(considerPosition))
        is StringLiteralExpressionContext -> StringLiteralExpression(text, toPosition(considerPosition))
        is FunctionOrClassInstanceCallExpressionContext -> toAst(considerPosition)
        is BinaryMathExpressionContext -> toAst(considerPosition)
        is BinaryLogicExpressionContext -> toAst(considerPosition)
        is BinaryComparisonExpressionContext -> toAst(considerPosition)
        is UnaryMathExpressionContext -> toAst(considerPosition)
        is UnaryLogicNegationExpressionContext -> toAst(considerPosition)
        is ParenthesisExpressionContext -> toAst(considerPosition)
        is VarReferenceExpressionContext -> toAst(considerPosition)
        is ListOfExpressionContext -> toAst(considerPosition)
        is InputExpressionContext -> toAst(considerPosition)
        is OutputExpressionContext -> toAst(considerPosition)
        is ObjectMethodCallExpressionContext -> toAst(considerPosition)
        is ObjectPropertyReferenceExpressionContext -> toAst(considerPosition)
        is PreIncrementExpressionContext -> PreIncrementExpression(this.ID().text, toPosition(considerPosition))
        is PostIncrementExpressionContext -> PostIncrementExpression(this.ID().text, toPosition(considerPosition))
        is PreDecrementExpressionContext -> PreDecrementExpression(this.ID().text, toPosition(considerPosition))
        is PostDecrementExpressionContext -> PostDecrementExpression(this.ID().text, toPosition(considerPosition))
        else -> throw NotImplementedError("${this.javaClass.kotlin.simpleName} not implemented")
    }
}

//task2
fun VarReferenceExpressionContext.toAst(considerPosition: Boolean): VarReferenceExpression {
    val name = this.value.text
    return VarReferenceExpression(
        name,
        toPosition(considerPosition)
    )
}

fun BinaryMathExpressionContext.toAst(considerPosition: Boolean): BinaryMathExpression {

    val left = this.left.toAst(considerPosition)
    val right = this.right.toAst(considerPosition)
    val operand: MathOperand = when (this.operand.text) {
        "+" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.plus
        "-" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.minus
        "*" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.times
        "/" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.division
        "|" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.module
        else -> throw NotImplementedError()
    }

    return BinaryMathExpression(
        operand,
        left,
        right,
        toPosition(considerPosition),
    )
}

fun BinaryLogicExpressionContext.toAst(considerPosition: Boolean): BinaryLogicExpression {

    val left = this.left.toAst(considerPosition)
    val right = this.right.toAst(considerPosition)
    val operand: LogicOperand = when (this.operand.text) {
        "&&" -> LogicOperand.and
        "||" -> LogicOperand.or
        else -> throw NotImplementedError()
    }

    return BinaryLogicExpression(
        operand,
        left,
        right,
        toPosition(considerPosition),
    )
}

fun BinaryComparisonExpressionContext.toAst(considerPosition: Boolean): BinaryComparisonExpression {

    val left = this.left.toAst(considerPosition)
    val right = this.right.toAst(considerPosition)

    val operand = when (this.operand.text) {
        "==" -> ComparisonOperand.equal
        "!=" -> ComparisonOperand.notEqual
        "<" -> ComparisonOperand.lessThan
        "<=" -> ComparisonOperand.lessThanOrEqual
        ">" -> ComparisonOperand.greaterThan
        ">=" -> ComparisonOperand.greaterThanOrEqual
        else -> throw NotImplementedError()
    }

    return BinaryComparisonExpression(
        operand,
        left,
        right,
        toPosition(considerPosition),
    )
}

fun UnaryMathExpressionContext.toAst(considerPosition: Boolean): UnaryMathExpression {

    val value = this.expression().toAst(considerPosition)
    val operand: MathOperand = when (this.operand.text) {
        "+" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.plus
        "-" -> org.i3biscotti.kotlin2ast.ast.models.MathOperand.minus
        else -> throw NotImplementedError()
    }

    return UnaryMathExpression(
        toPosition(considerPosition),
        operand,
        value
    )
}

fun UnaryLogicNegationExpressionContext.toAst(considerPosition: Boolean): UnaryLogicNegationExpression {

    val value = this.expression().toAst(considerPosition)

    return UnaryLogicNegationExpression(
        toPosition(considerPosition),
        value
    )
}

fun ParenthesisExpressionContext.toAst(considerPosition: Boolean): ParenthesisExpression {

    val value = this.expression().toAst(considerPosition)

    return ParenthesisExpression(
        value,
        toPosition(considerPosition)
    )
}

fun ListOfExpressionContext.toAst(considerPosition: Boolean): ListOfExpression {
    val listStatement = listOfDefinition()
    val items = listStatement.expression().map { it.toAst(considerPosition) }

    return ListOfExpression(
        items,
        toPosition(considerPosition)
    )
}

fun InputExpressionContext.toAst(considerPosition: Boolean): InputExpression {
    return InputExpression(
        toPosition(considerPosition)
    )
}

fun OutputExpressionContext.toAst(considerPosition: Boolean): OutputExpression {
    val value = this.value?.toAst(considerPosition)
    return OutputExpression(
        value,
        toPosition(considerPosition)
    )
}

fun FunctionOrClassInstanceCallExpressionContext.toAst(considerPosition: Boolean): FunctionCallExpression {
    val fn = functionOrClassInstanceCall()
    val fnName = fn.name.text
    val parameters = fn.expression().map { it.toAst(considerPosition) }

    return FunctionCallExpression(fnName, parameters, toPosition(considerPosition))
}

fun ObjectMethodCallExpressionContext.toAst(considerPosition: Boolean): ObjectMethodCallExpression {
    val objectMethodCall = objectMethodCall()
    val method = objectMethodCall.functionOrClassInstanceCall()
    val params = method.expression().map { it.toAst(considerPosition) }

    return ObjectMethodCallExpression(
        objectMethodCall.objectName.text,
        method.name.text,
        params,
        toPosition(considerPosition)
    )
}

fun ObjectPropertyReferenceExpressionContext.toAst(considerPosition: Boolean): ObjectPropertyReferenceExpression {
    val objectProp = objectProperty()

    return ObjectPropertyReferenceExpression(
        objectProp.objectName.text,
        objectProp.propertyName.text,
        toPosition(considerPosition)
    )
}