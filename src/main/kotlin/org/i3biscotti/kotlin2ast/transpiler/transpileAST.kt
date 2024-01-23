package org.i3biscotti.kotlin2ast.transpiler

import org.i3biscotti.kotlin2ast.ast.models.*
import java.lang.UnsupportedOperationException

val space = "    "

fun generateIdentationSpace( depth: Int) = space.repeat(depth)

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

fun Statement.transpile(depth: Int = 0): String {
    return when (this) {
        is VarDeclarationStatement -> transpile(depth)
        is AssignmentStatement -> transpile(depth)
        is ReturnStatement -> transpile(depth)
        is FunctionDefinitionStatement -> transpile(depth)
        else -> throw NotImplementedError()
    }
}
fun VarDeclarationStatement.transpile(depth: Int = 0): String {
    val variableTypeTranspiled = when (varType) {
        VariableType.immutable -> "val"
        VariableType.variable -> "var"
        VariableType.constant -> "const"
    }

    var declarationTranspiled = "$variableTypeTranspiled $name"

    val valueTypeTranspiled = when (valueType) {
        VariableValueType.INT -> "Int"
        VariableValueType.BOOLEAN -> "Boolean"
        VariableValueType.DOUBLE -> "Double"
        VariableValueType.STRING -> "String"
        VariableValueType.VOID -> "Unit"
        null -> ""
        else -> valueType.name
    }

    if (valueTypeTranspiled.isNotEmpty()) {
        declarationTranspiled = "$declarationTranspiled : $valueTypeTranspiled"
    }

    declarationTranspiled = "${generateIdentationSpace(depth)}$declarationTranspiled = ${value.transpile()}"

    return declarationTranspiled
}

fun AssignmentStatement.transpile(depth: Int = 0): String {
    val valueTranspiled = value.transpile()
    return "${generateIdentationSpace(depth)}$name = $valueTranspiled"
}

fun FunctionDefinitionStatement.transpile(depth: Int = 0): String {
    var functionStatement = "fun $name"

    if (parameters.isNotEmpty()) {
        val params = parameters.joinToString(", ") {
            val type = when (it.valueType) {
                VariableValueType.INT -> "Int"
                VariableValueType.BOOLEAN -> "Boolean"
                VariableValueType.DOUBLE -> "Double"
                VariableValueType.STRING -> "String"
                VariableValueType.VOID -> "Unit"
                else -> it.valueType.name
            }
            "${it.name} : $type" }

        functionStatement += "($params)"
    } else {
        functionStatement += "()"
    }

    if (returnType != null) {
        val rType = when (returnType) {
            VariableValueType.INT -> "Int"
            VariableValueType.BOOLEAN -> "Boolean"
            VariableValueType.DOUBLE -> "Double"
            VariableValueType.STRING -> "String"
            VariableValueType.VOID -> "Unit"
            else -> returnType.name
        }

        functionStatement += " : $rType"
    }


    if (statements.isNotEmpty()) {
        val statementsTranspiled = statements.joinToString("\n") { it.transpile(depth+1) }
        functionStatement = """
        |${generateIdentationSpace(depth)}$functionStatement {
        |$statementsTranspiled
        |${generateIdentationSpace(depth)}}
        """.trimMargin()
    } else {
        functionStatement = "${generateIdentationSpace(depth)}$functionStatement {}"
    }

    return functionStatement
}

fun ReturnStatement.transpile(depth: Int = 0): String {
    return generateIdentationSpace(depth) + if (value != null) {
        val valueTranspiled = value.transpile()
        "return $valueTranspiled"
    } else {
        "return"
    }
}

fun Expression.transpile(): String {
    return when (this) {
        is IntLiteralExpression -> value
        is DecLit -> value
        is BooleanLitExpression -> value
        is StringLit -> value
        is BinaryMathExpression -> transpile()
        is BinaryLogicExpression -> transpile()
        is UnaryMathExpression -> transpile()
        is UnaryLogicNegationExpression -> transpile()
        is VarReferenceExpression -> transpile()
        is ParenthesisExpression -> transpile()
        is FunctionCallExpression -> transpile()
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
    return "$leftTranspiled $operand $rightTranspiled"
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
    return "$leftTranspiled $operand $rightTranspiled"
}

fun UnaryLogicNegationExpression.transpile(): String {
    return "! ${value.transpile()}"
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

fun FunctionCallExpression.transpile(): String {
    val params = parameters.map { it.transpile() }.joinToString(", ")
    return "$name($params)"
}