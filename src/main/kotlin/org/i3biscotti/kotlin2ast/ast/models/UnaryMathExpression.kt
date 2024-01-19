package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("UnaryMathExpression")
data class UnaryMathExpression(
    override val position: Position?,
    val operand: MathOperand,
    override val value: Expression,
) : UnaryExpression()