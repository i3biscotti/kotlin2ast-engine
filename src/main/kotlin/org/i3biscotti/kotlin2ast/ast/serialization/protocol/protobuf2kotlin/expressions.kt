package org.i3biscotti.kotlin2ast.ast.serialization.protocol.protobuf2kotlin

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.Expressions

fun Expressions.Expression.toAst(): Expression {
    if (hasIntLit()) {
        return intLit.toAst()
    } else if (hasStringLit()) {
        return stringLit.toAst()
    } else if (hasStringLit()) {
        return stringLit.toAst()
    } else {
        throw IllegalArgumentException("Unknown expression type")
    }

}

fun Expressions.IntLit.toAst(): IntLiteralExpression {
    return IntLiteralExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.StringLit.toAst(): StringLiteralExpression {
    return StringLiteralExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.DecLit.toAst(): DoubleLiteralExpression {
    return DoubleLiteralExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}