package org.i3biscotti.kotlin2ast.ast.models






data class StringLiteralExpression(
    val value: String,
    override val position: Position?
) : Expression()