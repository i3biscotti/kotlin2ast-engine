package org.i3biscotti.kotlin2ast.ast.models

data class BinaryComparisonExpression(
    val operand: ComparisonOperand,
    override val left: Expression,
    override val right: Expression,
    override val position: Position?,
) : BinaryExpression()