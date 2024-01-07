package org.i3biscotti.kotlin2ast.ast

fun Node.serialize(): Map<String, Any> {
    return when (this) {
        is ProgramFile -> serialize()
        is Statement -> serialize()
        is Expression -> serialize()
        else -> throw NotImplementedError()
    }
}

fun ProgramFile.serialize():Map <String,Any> {
    val statements = lines.map{ it.serialize() }

    val file =  mapOf(
        "nodeType" to  javaClass.name,
        "lines" to statements
    )

    return file
}

fun Statement.serialize():  Map<String,Any> {
    return when (this) {
        is VarDeclarationStatement -> serialize()
        is AssignmentStatement -> serialize()
        else -> throw NotImplementedError()
    }
}

fun  VarDeclarationStatement.serialize(): Map<String,Any> {
    val valueSerialized = value.serialize()
    val valueTypeSerialized = when (valueType) {
        VariableValueType.Int -> ": Int"
        VariableValueType.Boolean -> ": Boolean"
        VariableValueType.Double -> ": Double"
        VariableValueType.String -> ": String"
        VariableValueType.Reference -> ""
        null -> ""
    }

    val variableTypeSerialized = when (type) {
        VariableType.immutable -> "val"
        VariableType.variable -> "var"
        VariableType.constant -> "const"
    }

    return mapOf(
        "nodeType" to  javaClass.name,
        "type" to variableTypeSerialized,
        "name" to name,
        "valueType" to valueTypeSerialized,
        "value" to valueSerialized
    )
}

fun  AssignmentStatement.serialize(): Map<String, Any> {
    val valueSerialized = value.serialize()

    return  mapOf(
        "nodeType" to javaClass.name,
        "name" to name,
        "value" to valueSerialized
    )
}

fun Expression.serialize(): Map<String, Any> {
    val valueSerialized = when (this) {
        is IntLit -> value
        is DecLit -> value
        is BooleanLit -> value
        is StringLit -> value
        else -> throw NotImplementedError()
    }

    return mapOf(
        "nodeType" to  javaClass.name,
        "value" to valueSerialized
    )
}