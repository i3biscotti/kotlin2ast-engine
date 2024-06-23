package org.i3biscotti.kotlin2ast.ast.models






data class BinaryLogicExpression(
    val operand: LogicOperand,
    override val left: Expression,
    override val right: Expression,
    override val position: Position?,
) : BinaryExpression()