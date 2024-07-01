package org.i3biscotti.kotlin2ast.transpiler

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.transpileRequest
import kotlin.UnsupportedOperationException

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
        is VariableDeclarationStatement -> transpile(depth)
        is AssignmentStatement -> transpile(depth)
        is ReturnStatement -> transpile(depth)
        is FunctionDefinitionStatement -> transpile(depth)
        is ExpressionDefinitionStatement -> transpile(depth)
        is IfDefinitionStatement -> transpile(depth)
        is WhileDefinitionStatement -> transpile(depth)
        is ForDefinitionStatement -> transpile(depth)
        is AssignmentForStatement -> transpile(depth)
        is ExpressionForStatement -> transpile(depth)
        is VarDeclarationForStatement -> transpile(depth)
        is ClassDefinitionStatement -> transpile(depth)
        is ConstructorDefinitionStatement -> transpile(depth)
        is ObjectPropertyAssignmentStatement -> transpile(depth)
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

fun VariableDeclarationStatement.transpile(depth: Int = 0): String {
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

    declarationTranspiled = "${generateIndentationSpace(depth)}$declarationTranspiled = ${value?.transpile()}"

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
    val ifBlockTranspiled = ifBlock.transpile(depth)
    val elseIfBlockTranspiled = elseIfBlock?.joinToString(" "){ it.transpile(0) }
    val elseBlockTranspiled = elseBlock?.transpile(depth)
    var compositeIf = "${generateIndentationSpace(depth)}$ifBlockTranspiled"
    if (elseIfBlock != null && elseIfBlock.isNotEmpty()) {
        compositeIf += " $elseIfBlockTranspiled"
    }
    if (elseBlock != null) {
        compositeIf += " $elseBlockTranspiled"
    }
    return compositeIf

}

fun IfBlock.transpile(depth: Int = 0): String {
    var ifBlockTranspiled: String

    val ifBlockKeyword = when (blockType) {
        BlockType.IfBlock -> "if "
        BlockType.ElseIfBlock -> "else if "
        BlockType.ElseBlock -> "else"
    }

    ifBlockTranspiled = ifBlockKeyword

    if (condition != null) {
        ifBlockTranspiled += "(${condition.transpile()})"
    }

    if (statements.isNotEmpty()) {
        val statementsTranspiled = statements.joinToString("\n") { it.transpile(depth + 1) }
        ifBlockTranspiled = """
        |$ifBlockTranspiled {
        |$statementsTranspiled
        |${generateIndentationSpace(depth)}}
        """.trimMargin()
    } else {
        ifBlockTranspiled = "$ifBlockTranspiled {\n${generateIndentationSpace(depth)}}"
    }

    return ifBlockTranspiled
}

fun WhileDefinitionStatement.transpile(depth: Int = 0): String {
    val whileConditionTranspiled = whileCondition.transpile()
    val statementsTranspiled = statements.joinToString("\n") { it.transpile(depth + 1) }

    return """
        |while ($whileConditionTranspiled) {
        |$statementsTranspiled
        |${generateIndentationSpace(depth)}}
        """.trimMargin()
}

fun ForDefinitionStatement.transpile(depth: Int = 0): String {


    if (forCondition is StandardForCondition) {
    val initStatement = forCondition.initStatement
       lateinit var initStatementTranspiled : String

     if ( initStatement is VarDeclarationForStatement ){
         initStatementTranspiled =  VariableDeclarationStatement(
               initStatement.varType,
               initStatement.name,
               initStatement.valueType,
               initStatement.value,
               initStatement.position,
           ).transpile()
        }else if(initStatement is AssignmentForStatement){
         initStatementTranspiled =    AssignmentStatement(
               initStatement.name,
               initStatement.value,
               initStatement.position,
           ).transpile()
        }

        val whileStatements = mutableListOf<Statement>()
        whileStatements.addAll(statements)
        whileStatements.add(ExpressionDefinitionStatement(
           expression =  (forCondition.incrementStatement as ExpressionDefinitionStatement).expression,
           position =  null
        ))

        val forWhile = WhileDefinitionStatement(
            forCondition.controlExpression,
            whileStatements,
            position = position
        ).transpile(depth)

        return """
            |${generateIndentationSpace(depth)}$initStatementTranspiled
            |${generateIndentationSpace(depth)}$forWhile
        """.trimMargin()

    } else if(forCondition is ForEachCondition) {
        val forConditionTranspiled = forCondition.transpile()
        val statementsTranspiled = statements.joinToString("\n") { it.transpile(depth + 1) }

        return """
        |for ($forConditionTranspiled) {
        |$statementsTranspiled
        |${generateIndentationSpace(depth)}}
    """.trimMargin()
    }
        throw UnsupportedOperationException()

}

fun ForEachCondition.transpile(depth: Int = 0): String {
    val itemDefinition = this.itemDefinition.name
    val value = value.transpile()

    return "$itemDefinition in $value"
}

fun ListOfExpression.transpile(depth: Int = 0): String {
    val itemsTranspiled = items.map { it.transpile() }.joinToString(", ")
    return "${generateIndentationSpace(depth)}listOf($itemsTranspiled)"
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

fun ObjectPropertyAssignmentStatement.transpile(depth: Int = 0): String {
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
        is PreIncrementExpression -> "++$name"
        is PostIncrementExpression -> "$name++"
        is PreDecrementExpression -> "--$name"
        is PostDecrementExpression -> "$name--"
        is BinaryMathExpression -> transpile()
        is BinaryLogicExpression -> transpile()
        is BinaryComparisonExpression -> transpile()
        is UnaryMathExpression -> transpile()
        is UnaryLogicNegationExpression -> transpile()
        is VarReferenceExpression -> transpile()
        is ParenthesisExpression -> transpile()
        is ListOfExpression -> transpile()
        is InputExpression -> transpile()
        is OutputExpression -> transpile()
        is FunctionCallExpression -> transpile()
        is ObjectMethodCallExpression -> transpile()
        is ObjectPropertyReferenceExpression -> transpile()

    }
}

fun BinaryLogicExpression.transpile(): String {
    val leftTranspiled = left.transpile()
    val operand = when (this.operand) {
        LogicOperand.and -> "&&"
        LogicOperand.or -> "||"
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
fun BinaryComparisonExpression.transpile(): String {
    val leftTranspiled = left.transpile()

    val operand = when (this.operand) {
        ComparisonOperand.equal -> "=="
        ComparisonOperand.notEqual -> "!="
        ComparisonOperand.lessThan -> "<"
        ComparisonOperand.lessThanOrEqual -> "<="
        ComparisonOperand.greaterThan -> ">"
        ComparisonOperand.greaterThanOrEqual -> ">="
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

fun InputExpression.transpile(): String {
    return "readLine()"
}

fun OutputExpression.transpile(): String {
    val valueTranspile = value.transpile()
    return "println($valueTranspile)"
}

fun FunctionCallExpression.transpile(): String {
    val params = parameters.joinToString(", ") { it.transpile() }
    return "$name($params)"
}

fun ObjectMethodCallExpression.transpile(): String {
    val paramsTranspiledInline = params.joinToString(", ") { it.transpile() }
    return "$objectName.$methodName($paramsTranspiledInline)"
}

fun ObjectPropertyReferenceExpression.transpile(): String {
    return "$objectName.$propertyName"
}