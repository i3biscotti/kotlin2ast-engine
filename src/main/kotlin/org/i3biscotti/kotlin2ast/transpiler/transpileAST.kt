package org.i3biscotti.kotlin2ast.transpiler

import org.i3biscotti.kotlin2ast.ast.models.*

fun Node.transpile(): String {
    return when (this) {
        is ProgramFile -> transpile()
        is Statement -> transpile()
        is Expression -> transpile()
        else -> throw NotImplementedError()
    }
}

fun ProgramFile.transpile(): String {
    return lines.joinToString("\n") { it.transpile() }
}

fun Statement.transpile(): String {
    return when (this) {
        is VarDeclarationStatement -> transpile()
        is AssignmentStatement -> transpile()
        else -> throw NotImplementedError()
    }
}

fun VarDeclarationStatement.transpile(): String {
    val valueTypeTranspiled = when (valueType) {
        VariableValueType.INT -> ": Int"
        VariableValueType.BOOLEAN -> ": Boolean"
        VariableValueType.DOUBLE -> ": Double"
        VariableValueType.STRING -> ": String"
        VariableValueType.VOID -> ": Unit"
        else -> ": $name"
    }

    val variableTypeTranspiled = when (varType) {
        VariableType.immutable -> "val"
        VariableType.variable -> "var"
        VariableType.constant -> "const"
    }

    var declarationTranspiled = "$variableTypeTranspiled $name $valueTypeTranspiled"

    if (value != null) {
        val valueTranspiled = value.transpile()
        declarationTranspiled = "$declarationTranspiled= $valueTranspiled"
    }

    return declarationTranspiled
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