package org.i3biscotti.kotlin2ast.ast.mapping

import KotlinParser.*
import org.antlr.v4.runtime.*
import org.i3biscotti.kotlin2ast.ast.models.*

fun Token.startPoint(): Point {
    return Point(line, charPositionInLine + 1)
}

fun Token.endPoint(): Point {
    return Point(line, charPositionInLine + 1 + (text?.length ?: 0))
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

    return ProgramFile(astLines, toPosition(considerPosition))
}


fun ParameterContext.toAstPropertyDeclaration(considerPosition: Boolean): PropertyDeclaration {
    val name = ID().text
    val type = antlr4ToAstValueType(type())
    val propertyType = if (VAR() != null) {
        VariableType.variable
    } else if (VAL() != null) {
        VariableType.immutable
    } else {
        throw UnsupportedOperationException("Property $name not supported")
    }

    return PropertyDeclaration(
        propertyType,
        name,
        type!!,
        null,
        toPosition(considerPosition)
    )
}

fun antlr4ToAstValueType(type: TypeContext?): VariableValueType? {
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

fun ParameterContext.toAst(considerPosition: Boolean): Parameter {
    val paramName = ID().text
    val paramValueType = antlr4ToAstValueType(type())!!
    var paramType = ParameterType.TYPE

    if (VAR() != null || VAL() != null) {
        paramType = ParameterType.THIS
    }

    return Parameter(paramType, paramName, paramValueType, toPosition(considerPosition))
}
