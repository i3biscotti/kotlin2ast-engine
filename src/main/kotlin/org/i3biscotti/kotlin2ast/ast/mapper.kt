package org.i3biscotti.kotlin2ast.ast

import io.ktor.http.*
import org.antlr.v4.runtime.*
import kotlinParser.*

fun Token.startPoint(): Point {
    return Point(line, charPositionInLine)
}


fun Token.endPoint(): Point {
    return Point(line, charPositionInLine + (text?.length ?: 0))
}


fun ParserRuleContext.toPosition(considerPosition: Boolean): Position? {
    return if (considerPosition) {
        Position(start!!.startPoint(), stop!!.endPoint())
    } else {
        null
    }
}


fun KotlinFileContext.toAst(considerPosition: Boolean = false): ProgramFile {
    val astLines = mutableListOf<Statement>()

    for (line in this.line()) {
        val statement = line.statement()
        astLines.add(statement?.toAst(considerPosition)!!)
    }

    return ProgramFile(astLines, toPosition(considerPosition)!!)
}


fun StatementContext.toAst(considerPosition: Boolean = false): Statement {
    return when (this) {
        is VarDeclarationStatementContext -> toAst(considerPosition)
        is ValDeclarationStatementContext -> toAst(considerPosition)
        is ConstDeclarationStatementContext -> toAst(considerPosition)
        is AssignStatementContext -> toAst(considerPosition)
        is FunctionDefinitionStatementContext -> functionDefinition().toAst(considerPosition)
        else -> throw NotImplementedError()
    }
}

fun <TypeContext> antlr4ToAstValueType(type: TypeContext): VariableValueType? {
    return when (type) {
        is IntTypeContext -> VariableValueType.INT
        is DoubleTypeContext -> VariableValueType.DOUBLE
        is BooleanTypeContext -> VariableValueType.BOOLEAN
        is StringTypeContext -> VariableValueType.STRING
        is UnitTypeContext -> VariableValueType.VOID
        is CustomTypeContext -> VariableValueType(type.ID().text)
        null -> null
        else -> throw NotImplementedError("$type is not implemented")
    }
}


fun VarDeclarationStatementContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.constant,
        name,
        valueType,
        value,
        toPosition(considerPosition)!!
    )
}


fun ValDeclarationStatementContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.immutable,
        name,
        valueType,
        value,
        toPosition(considerPosition)!!
    )
}


fun ConstDeclarationStatementContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.constant,
        name,
        valueType,
        value,
        toPosition(considerPosition)!!
    )
}


fun AssignStatementContext.toAst(considerPosition: Boolean): AssignmentStatement {

    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)

    return AssignmentStatement(
        name,
        value,
        toPosition(considerPosition)!!
    )
}


fun ExpressionContext.toAst(considerPosition: Boolean): Expression {
    return when (this) {
        is BoolLiteralExpressionContext -> BooleanLit(text, toPosition(considerPosition))
        is IntLiteralExpressionContext -> IntLit(text, toPosition(considerPosition))
        is DoubleLiteralExpressionContext -> BooleanLit(text, toPosition(considerPosition))
        is StringLiteralExpressionContext -> StringLit(text, toPosition(considerPosition))
        is FunctionCallExpressionContext -> toAst(considerPosition)
        else -> throw NotImplementedError()
    }
}

fun FunctionCallExpressionContext.toAst(considerPosition: Boolean): FunctionCallExpression{
    val fnName = name.text
    val parameters = expression().map { it.toAst(considerPosition) }

    return FunctionCallExpression(fnName, parameters, toPosition(considerPosition))
}

fun ParameterContext.toAst(considerPosition: Boolean): Parameter {
    val paramName = ID().text
    val paramType = antlr4ToAstValueType(type())!!

    return Parameter(paramName, paramType, toPosition(considerPosition))
}

fun FunctionDefinitionContext.toAst(considerPosition: Boolean): FunctionDefinitionStatement {
    val nameText = name.text
    val returnType = antlr4ToAstValueType(returnType) ?: VariableValueType.VOID
    val parameters = parameter().map { it.toAst(considerPosition) }
    val statements = block().statement().map { it.toAst(considerPosition) }
    return FunctionDefinitionStatement(nameText, parameters, returnType, statements, toPosition(considerPosition))
}