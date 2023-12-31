package org.i3biscotti.kotlin2ast.ast

fun Expression.getType() : VariableValueType{
    return when(this){
        is IntLit -> VariableValueType.Int
        is BooleanLit -> VariableValueType.Boolean
        is DecLit -> VariableValueType.Double
        is StringLit -> VariableValueType.String
        else -> throw NotImplementedError()
    }
}