package org.i3biscotti.kotlin2ast.ast.models






data class FunctionCallExpression(
    val name: String,
    val parameters: List<Expression>,
    override val position: Position?
) : Expression()