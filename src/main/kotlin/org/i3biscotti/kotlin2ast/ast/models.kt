package org.i3biscotti.kotlin2ast.ast

import kotlinx.serialization.*

@Serializable
data class Point(val line: Int, val column: Int)

@Serializable
data class Position(val start: Point, val end: Point)

interface Node {
    val position: Position?

    val nodeType: String
        get() = javaClass.kotlin.simpleName!!
}

@Serializable
@SerialName("ProgramFile")
data class ProgramFile(val lines: List<Statement>, override val position: Position?) : Node

@Serializable
sealed class Statement : Node{
    abstract override val position: Position?
}

@Serializable
@SerialName("VarDeclarationStatement")
data class VarDeclarationStatement(
    val varType: VariableType,
    val name: String,
    val valueType: VariableValueType?,
    val value: Expression,
    override val position: Position?
) : Statement()

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

@Serializable
@SerialName("AssignmentStatement")
data class AssignmentStatement(
    val name: String,
    val value: Expression,
    override val position: Position?
) : Statement()

@Serializable
sealed class Expression :Node {
    abstract override val position: Position?
}

@Serializable
@SerialName("IntLit")
data class IntLit(val value: String, override val position: Position?) : Expression()

@Serializable
@SerialName("DecLit")
data class DecLit(val value: String, override val position: Position?) : Expression()

@Serializable
@SerialName("StringLit")
data class StringLit(val value: String, override val position: Position?) : Expression()

@Serializable
@SerialName("BooleanLit")
data class BooleanLit(val value: String, override val position: Position?) : Expression()
