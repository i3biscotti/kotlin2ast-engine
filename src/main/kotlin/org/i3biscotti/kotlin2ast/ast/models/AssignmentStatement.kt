package org.i3biscotti.kotlin2ast.ast.models






data class AssignmentStatement(
    val name: String,
    val value: Expression,
    override val position: Position?
) : Statement()