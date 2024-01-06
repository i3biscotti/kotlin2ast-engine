package org.i3biscotti.kotlin2ast.ast

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
        is VariableDeclarationStatementContext -> variableDeclaration().toAst(considerPosition)
        else -> throw NotImplementedError()
    }
}


fun VariableDeclarationContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    return when (this) {
        is VarDeclarationContext -> toAst(considerPosition)
        is ValDeclarationContext -> toAst(considerPosition)
        is ConstDeclarationContext -> toAst(considerPosition)
        else -> throw NotImplementedError()
    }
}


fun <TypeContext> antlr4ToAstValueType(type: TypeContext): VariableValueType {
    return when (type) {
        is IntTypeContext -> VariableValueType.Int
        is DoubleTypeContext -> VariableValueType.Double
        is BooleanTypeContext -> VariableValueType.Boolean
        is StringTypeContext -> VariableValueType.String
        is ReferenceTypeContext -> VariableValueType.Reference
        else -> throw NotImplementedError()
    }
}


fun VarDeclarationContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst()
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.constant,
        name,
        valueType,
        value,
        toPosition(considerPosition)!!
    )
}


fun ValDeclarationContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst()
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.immutable,
        name,
        valueType,
        value,
        toPosition(considerPosition)!!
    )
}


fun ConstDeclarationContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst()
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.constant,
        name,
        valueType,
        value,
        toPosition(considerPosition)!!
    )
}
