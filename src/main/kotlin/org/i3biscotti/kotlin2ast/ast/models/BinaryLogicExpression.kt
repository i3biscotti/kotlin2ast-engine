package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BinaryLogicExpression")
data class BinaryLogicExpression(
    override val position: Position?,
    val operand: LogicOperand,
    override val left: Expression,
    override val right: Expression
) : BinaryExpression()