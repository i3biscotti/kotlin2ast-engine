package org.i3biscotti.kotlin2ast.ast.models






data class ListOfExpression(
    val items: List<Expression>,
    override val position: Position?,
) : Expression()