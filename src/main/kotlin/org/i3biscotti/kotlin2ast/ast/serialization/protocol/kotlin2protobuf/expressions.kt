package org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf

import org.i3biscotti.kotlin2ast.ast.models.*

fun Expression.toProtobuf(): protocol.Expressions.Expression {
    return when (this) {
        is IntLiteralExpression -> TODO()
        is DoubleLiteralExpression -> TODO()
        is StringLiteralExpression -> TODO()
        is BooleanLitExpression -> TODO()
        is FunctionCallExpression -> TODO()
        is BinaryLogicExpression -> TODO()
        is BinaryMathExpression -> TODO()
        is ListOfExpression -> TODO()
        is ObjectMethodCallExpression -> TODO()
        is ParenthesisExpression -> TODO()
        is UnaryLogicNegationExpression -> TODO()
        is UnaryMathExpression -> TODO()
        is VarReferenceExpression -> TODO()
        is ObjectPropertyReferenceExpression -> TODO()
        is BinaryComparisonExpression -> TODO()
        is PreDecrementExpression -> TODO()
        is PostIncrementExpression -> TODO()
        is PostDecrementExpression -> TODO()
        is PreIncrementExpression -> TODO()
    }
}