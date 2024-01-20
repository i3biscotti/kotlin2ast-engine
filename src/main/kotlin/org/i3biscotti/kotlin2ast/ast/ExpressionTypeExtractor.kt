package org.i3biscotti.kotlin2ast.ast

import org.i3biscotti.kotlin2ast.ast.models.*

fun Expression.getType(): VariableValueType {
    return when (this) {
        is IntLit -> VariableValueType.INT
        is BooleanLitExpression -> VariableValueType.BOOLEAN
        is DecLit -> VariableValueType.DOUBLE
        is StringLit -> VariableValueType.STRING
        else -> throw NotImplementedError()
    }
}