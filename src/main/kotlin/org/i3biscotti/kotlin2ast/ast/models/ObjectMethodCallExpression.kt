package org.i3biscotti.kotlin2ast.ast.models






data class ObjectMethodCallExpression(
    val objectName: String,
    val methodName: String,
    val params: List<Expression>,
    override val position: Position?
) : Expression()