package org.i3biscotti.kotlin2ast.ast

fun Expression.getType() : VariableValueType{
    return when(this){
        is IntLit -> VariableValueType.INT
        is BooleanLit -> VariableValueType.BOOLEAN
        is DecLit -> VariableValueType.DOUBLE
        is StringLit -> VariableValueType.STRING
        else -> throw NotImplementedError()
    }
}