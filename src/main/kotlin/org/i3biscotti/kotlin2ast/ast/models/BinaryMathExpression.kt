package org.i3biscotti.kotlin2ast.ast.models






data class BinaryMathExpression(
    val operand: MathOperand,
    override val left: Expression,
    override val right: Expression,
    override val position: Position?,
) : BinaryExpression()