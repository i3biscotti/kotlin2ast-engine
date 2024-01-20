package org.i3biscotti.kotlin2ast.transpiler

import org.i3biscotti.kotlin2ast.ast.models.*
import java.lang.UnsupportedOperationException

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
        is BooleanLitExpression -> value
        is StringLit -> value
        else -> throw NotImplementedError()
    }
}

fun BinaryLogicExpression.transpile(): String {
    val leftTranspiled = left.transpile()
    val operand = when (this.operand) {
        LogicOperand.and -> "&&"
        LogicOperand.or -> "||"
        LogicOperand.equal -> "=="
        LogicOperand.notEqual -> "!="
        LogicOperand.lessThan -> "<"
        LogicOperand.lessThanOrEqual -> "<="
        LogicOperand.greaterThan -> ">"
        LogicOperand.greaterThanOrEqual -> ">="
        LogicOperand.not -> throw UnsupportedOperationException()
    }
    val rightTranspiled = right.transpile()
    return "$leftTranspiled + $operand + $rightTranspiled"
}

fun BinaryMathExpression.transpile(): String {
    val leftTranspiled = left.transpile()
    val operand = when (this.operand) {
        MathOperand.plus -> "+"
        MathOperand.minus -> "-"
        MathOperand.times -> "*"
        MathOperand.division -> "/"
        MathOperand.module -> "|"
    }
    val rightTranspiled = right.transpile()
    return "$leftTranspiled + $operand + $rightTranspiled"
}

fun UnaryLogicNegationExpression.transpile(): Expression {
    return value
}

fun UnaryMathExpression.transpile(): String {
    val valueTranspiled = value.transpile()
    val operand = when (this.operand) {
        MathOperand.plus -> "+"
        MathOperand.minus -> "-"
        MathOperand.times -> throw UnsupportedOperationException()
        MathOperand.division -> throw UnsupportedOperationException()
        MathOperand.module -> throw UnsupportedOperationException()
    }
    return "$operand $valueTranspiled"
}

fun VarReferenceExpression.transpile(): String {
    return name
}

fun ParenthesisExpression.transpile(): String {
    val valueTranspiled = value.transpile()
    return "( $valueTranspiled )"
}