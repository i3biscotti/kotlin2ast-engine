package org.i3biscotti.kotlin2ast.ast.models

data class AssignmentForStatement(
    val name: String,
    val value: Expression,
    override val position: Position?
) : ForInitOrIncrementStatement()