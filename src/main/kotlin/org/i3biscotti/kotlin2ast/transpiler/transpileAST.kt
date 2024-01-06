package org.i3biscotti.kotlin2ast.transpiler

import org.i3biscotti.kotlin2ast.ast.*

fun Node.transpile(): String {
    return when (this) {
        is ProgramFile -> transpile()
        is Statement -> transpile()
        is Expression -> transpile()
        else -> throw NotImplementedError()
    }
}

fun ProgramFile.transpile(): String {
    return lines.joinToString("\n") { transpile() }
}

fun Statement.transpile(): String {
    return when (this) {
        is VarDeclarationStatement -> transpile()
        is AssignmentStatement -> transpile()
        else -> throw NotImplementedError()
    }
}

fun VarDeclarationStatement.transpile(): String {
    val valueTranspiled = value.transpile()
    val valueTypeTranspiled = when (valueType) {
        VariableValueType.Int -> ": Int"
        VariableValueType.Boolean -> ": Boolean"
        VariableValueType.Double -> ": Double"
        VariableValueType.String -> ": String"
        VariableValueType.Reference -> ": object"
        null -> ""
    }

    val variableTypeTranspiled = when (type) {
        VariableType.immutable -> "val"
        VariableType.variable -> "var"
        VariableType.constant -> "const"
    }

    return "$variableTypeTranspiled $name $valueTypeTranspiled = $valueTranspiled"
}

fun AssignmentStatement.transpile(): String {
    val valueTranspiled = value.transpile()

    return "$name = $valueTranspiled"
}

fun Expression.transpile(): String {
    return when (this) {
        is IntLit -> value
        is DecLit -> value
        is BooleanLit -> value
        is StringLit -> value
        else -> throw NotImplementedError()
    }
}