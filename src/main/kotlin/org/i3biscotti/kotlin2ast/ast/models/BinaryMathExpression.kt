package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BinaryMathExpression")
data class BinaryMathExpression(
    val operand: MathOperand,
    override val left: Expression,
    override val right: Expression,
    override val position: Position?,
) : BinaryExpression()