package org.i3biscotti.kotlin2ast.transpiler

import org.i3biscotti.kotlin2ast.ast.models.*
import java.lang.UnsupportedOperationException

val space = "    "

fun generateIndentationSpace(depth: Int) = space.repeat(depth)

fun Node.transpile(): String {
    return when (this) {
        is ProgramFile -> transpile()
        is Statement -> transpile()
        is Expression -> transpile()
        else -> throw NotImplementedError()
    }
}

fun ProgramFile.transpile(): String {
    return lines.joinToString("\n") { it.transpile(0) }
}

fun Statement.transpile(depth: Int = 0): String {
    return when (this) {
        is VarDeclarationStatement -> transpile(depth)
        is AssignmentStatement -> transpile(depth)
        is ReturnStatement -> transpile(depth)
        is FunctionDefinitionStatement -> transpile(depth)
        is ExpressionDefinitionStatement -> transpile(depth)
        is IfDefinitionStatement -> transpile(depth)
        is WhileDefinitionStatement -> transpile(depth)
        is ForDefinitionStatement -> transpile(depth)
        is ClassDefinitionStatement -> transpile(depth)
        is ConstructorDefinitionStatement -> transpile(depth)
        is ObjectPropertyAssignmentStatement -> transpile(depth)
        is ElseBlock -> transpile(depth)
        is ElseIfBlock -> transpile(depth)
        is IfBlock -> transpile(depth)
    }
}

fun VariableValueType.transpile(): String {
    return when (this) {
        VariableValueType.INT -> "Int"
        VariableValueType.BOOLEAN -> "Boolean"
        VariableValueType.DOUBLE -> "Double"
        VariableValueType.STRING -> "String"
        VariableValueType.VOID -> "Unit"
        else -> name
    }
}

fun VarDeclarationStatement.transpile(depth: Int = 0): String {
    val variableTypeTranspiled = when (varType) {
        VariableType.immutable -> "val"
        VariableType.variable -> "var"
        VariableType.constant -> "const"
    }

    var declarationTranspiled = "$variableTypeTranspiled $name"

    val valueTypeTranspiled = valueType?.transpile() ?: ""

    if (valueTypeTranspiled.isNotEmpty()) {
        declarationTranspiled = "$declarationTranspiled : $valueTypeTranspiled"
    }

    declarationTranspiled = "${generateIndentationSpace(depth)}$declarationTranspiled = ${value.transpile()}"

    return declarationTranspiled
}

fun ExpressionDefinitionStatement.transpile(depth: Int = 0): String {
    val expressionTranspiled = expression.transpile()
    return "${generateIndentationSpace(depth)}$expressionTranspiled"
}

fun AssignmentStatement.transpile(depth: Int = 0): String {
    val valueTranspiled = value.transpile()
    return "${generateIndentationSpace(depth)}$name = $valueTranspiled"
}

fun IfDefinitionStatement.transpile(depth: Int = 0): String {
    val ifBlockTranspiled = ifBlock.transpile()
    val elseIfBlockTranspiled = elseIfBlock?.map{it.transpile()}
    val elseBlockTranspiled = elseBlock?.transpile()
    return "${generateIndentationSpace(depth)} $ifBlockTranspiled $elseIfBlockTranspiled $elseBlockTranspiled"
}

fun WhileDefinitionStatement.transpile(depth: Int = 0): String {
    val WhileConditionTranspiled = whileCondition.transpile()
    return "${generateIndentationSpace(depth)} $WhileConditionTranspiled"
}

fun ForDefinitionStatement.transpile(depth: Int = 0): String {
    val ForConditionTranspiled = forCondition.transpile()
    return "${generateIndentationSpace(depth)} $ForConditionTranspiled"
}

fun ListOfExpression.transpile(depth: Int = 0): String {
    val itemsTraspiled = items.map { it.transpile() }
    return "${generateIndentationSpace(depth)} $itemsTraspiled"
}

fun FunctionDefinitionStatement.transpile(depth: Int = 0): String {
    var functionStatement = "fun $name"

    if (parameters.isNotEmpty()) {
        val params = parameters.joinToString(", ") {
            val type = it.valueType.transpile()
            "${it.name}: $type"
        }

        functionStatement += "($params)"
    } else {
        functionStatement += "()"
    }

    if (returnType != null) {
        val rType = returnType.transpile()
        functionStatement += " : $rType"
    }


    if (statements.isNotEmpty()) {
        val statementsTranspiled = statements.joinToString("\n") { it.transpile(depth + 1) }
        functionStatement = """
        |${generateIndentationSpace(depth)}$functionStatement {
        |$statementsTranspiled
        |${generateIndentationSpace(depth)}}
        """.trimMargin()
    } else {
        functionStatement = "${generateIndentationSpace(depth)}$functionStatement {}"
    }

    return functionStatement
}

fun ClassDefinitionStatement.transpile(depth: Int = 0): String {
    var classStatement = "class $name"

    if (isPrivate) {
        classStatement = "private $classStatement"
    }

    var classBodyBlock = "{}"

    if (methods.isNotEmpty() || constructors.isNotEmpty()) {
        val mainConstructor = constructors.firstOrNull { it.thisConstructor == null }

        var initBlockTranspiled = ""

        if (mainConstructor != null) {
            val mainConstructorParamsTranspiled = mainConstructor.parameters.map {
                val paramName = it.name
                val propFound = properties.firstOrNull { it.name == paramName }
                val valueTypeTranspiled = it.valueType.transpile()

                if (propFound != null) {
                    val propKeyword = when (propFound.varType) {
                        VariableType.variable -> "var"
                        VariableType.immutable -> "val"
                        VariableType.constant -> "const"
                    }

                    "$propKeyword $paramName : $valueTypeTranspiled"
                } else {
                    "$paramName : $valueTypeTranspiled"
                }
            }.joinToString(", ")

            if (mainConstructorParamsTranspiled.isNotEmpty()) {
                classStatement = "$classStatement($mainConstructorParamsTranspiled)"
            }

            if (mainConstructor.body.isNotEmpty()) {
                val initStatements = mainConstructor.body.joinToString("\n") {
                    it.transpile(depth + 2)
                }

                initBlockTranspiled = """
                    |${generateIndentationSpace(depth + 1)}init {
                    |$initStatements
                    |${generateIndentationSpace(depth + 1)}}
                    """.trimMargin()

            }
        }

        val otherConstructors = constructors
            .filter { it.thisConstructor != null }
            .joinToString("\n ") { it.transpile(depth) }

        val methodsTranspiled = methods.joinToString("\n") { it.transpile(depth + 1) }

        classBodyBlock = "{"
        var mustAddLineBreak = false

        if (initBlockTranspiled.isNotEmpty()) {
            mustAddLineBreak = true
            classBodyBlock += "\n$initBlockTranspiled"
        }

        if (otherConstructors.isNotEmpty()) {
            mustAddLineBreak = true
            classBodyBlock += "\n$otherConstructors"
        }

        if (methodsTranspiled.isNotEmpty()) {
            mustAddLineBreak = true
            classBodyBlock += "\n$methodsTranspiled"
        }

        if (mustAddLineBreak) {
            classBodyBlock += "\n"
        }

        classBodyBlock += "}"
    }

    if (parentClassType != null) {
        classStatement = "$classStatement : ${parentClassType.name}"
    }

    classStatement = "${generateIndentationSpace(depth)}$classStatement $classBodyBlock"

    return classStatement
}

fun ConstructorDefinitionStatement.transpile(depth: Int = 0): String {
    val params = parameters.joinToString(", ") { "${it.name} : ${it.valueType.transpile()}" }
    val thisConstructorParams = thisConstructor!!.parameters
        .joinToString(", ") { it.transpile() }

    val constructorBlock = if (body.isNotEmpty()) {
        val statements = body.joinToString("\n")
        { it.transpile(depth + 2) }

        """
        | {
        |$statements
        |${generateIndentationSpace(depth + 1)}}
        """.trimMargin()
    } else {
        ""
    }

    return "${generateIndentationSpace(depth + 1)}constructor($params) : this($thisConstructorParams)$constructorBlock"
}

fun ObjectPropertyAssignmentStatement.transpile(depth: Int = 0) : String{
    return "${generateIndentationSpace(depth)}$objectName.$propertyName = ${value.transpile()}"
}

fun ReturnStatement.transpile(depth: Int = 0): String {
    return generateIndentationSpace(depth) + if (value != null) {
        val valueTranspiled = value.transpile()
        "return $valueTranspiled"
    } else {
        "return"
    }
}

fun Expression.transpile(): String {
    return when (this) {
        is IntLiteralExpression -> value
        is DoubleLiteralExpression -> value
        is BooleanLitExpression -> value
        is StringLiteralExpression -> value
        is BinaryMathExpression -> transpile()
        is BinaryLogicExpression -> transpile()
        is UnaryMathExpression -> transpile()
        is UnaryLogicNegationExpression -> transpile()
        is VarReferenceExpression -> transpile()
        is ParenthesisExpression -> transpile()
        is ListOfExpression -> transpile()
        is FunctionCallExpression -> transpile()
        is ObjectMethodCallExpression -> transpile()
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
    return "!${value.transpile()}"
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
    return "($valueTranspiled)"
}

fun FunctionCallExpression.transpile(): String {
    val params = parameters.joinToString(", ") { it.transpile() }
    return "$name($params)"
}

fun ObjectMethodCallExpression.transpile(): String {
    val paramsTranspiledInline = params.joinToString(", ") { it.transpile() }
    return "$objectName.$methodName($paramsTranspiledInline)"
}