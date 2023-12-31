package org.i3biscotti.kotlin2ast.ast

import org.antlr.v4.gui.PostScriptDocument
import java.lang.Thread.State

data class Point(val line: Int, val column: Int)

data class Position(val start: Point, val end: Point)

open class Node(position: Position)

data class KotlinFile(val lines: List<Statement>, val position: Position) : Node(position)

open class Statement(position: Position) : Node(position)

data class VarDeclarationStatement(
    val type: VariableType,
    val name: String,
    val valueType: VariableValueType,
    val value: Expression,
    val position: Position
) : Statement(position)

enum class VariableValueType {
    Int,
    Double,
    String,
    Boolean,
    Reference
}

enum class VariableType {
    variable, immutable, constant
}

open class Expression(position: Position) : Node(position)

data class Assignment(
    val name: String,
    val value: Expression,
    val position: Position
) : Statement(position)

data class initLit(val value: String, val position: Position) : Expression(position)

data class DecLit(val value: String, val position: Position) : Expression(position)

data class StringLit(val value: String, val position: Position) : Expression(position)

data class BooleanLit(val value: String, val position: Position) : Expression(position)
