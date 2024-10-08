package org.i3biscotti.kotlin2ast.ast.models

data class ExpressionForStatement(
    val value: Expression,
    override val position: Position?
) : ForInitOrIncrementStatement()