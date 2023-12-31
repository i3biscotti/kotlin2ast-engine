package org.i3biscotti.kotlin2ast.ast

import org.antlr.v4.gui.PostScriptDocument
import java.lang.Thread.State

data class Point(val line: Int, val column: Int)

data class Position(val start: Point, val end: Point)

interface Node {
    val position: Position?
}

data class KotlinFile(val lines: List<Statement>, override val position: Position?) : Node

open class Statement(override val position: Position?) : Node

data class VarDeclarationStatement(
    val type: VariableType,
    val name: String,
    val valueType: VariableValueType?,
    val value: Expression,
    override val position: Position?
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

open class Expression(override val position: Position?) : Node

data class Assignment(
    val name: String,
    val value: Expression,
    override val position: Position?
) : Statement(position)

data class IntLit(val value: String, override val position: Position?) : Expression(position)

data class DecLit(val value: String, override val position: Position?) : Expression(position)

data class StringLit(val value: String, override val position: Position?) : Expression(position)

data class BooleanLit(val value: String, override val position: Position?) : Expression(position)
