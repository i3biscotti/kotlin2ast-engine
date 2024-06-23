package org.i3biscotti.kotlin2ast.ast.models






data class UnaryMathExpression(
    override val position: Position?,
    val operand: MathOperand,
    override val value: Expression,
) : UnaryExpression()