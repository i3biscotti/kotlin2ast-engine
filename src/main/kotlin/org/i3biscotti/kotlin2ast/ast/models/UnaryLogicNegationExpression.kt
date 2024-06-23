package org.i3biscotti.kotlin2ast.ast.models






data class UnaryLogicNegationExpression(
    override val position: Position?,
    override val value: Expression
) : UnaryExpression()