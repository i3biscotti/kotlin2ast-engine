package org.i3biscotti.kotlin2ast.ast.models

data class StandardForCondition(
    val initStatement: ForInitOrIncrementStatement,
    val controlExpression: Expression,
    val incrementStatement: ForInitOrIncrementStatement,
    override val position: Position?
): ForCondition()