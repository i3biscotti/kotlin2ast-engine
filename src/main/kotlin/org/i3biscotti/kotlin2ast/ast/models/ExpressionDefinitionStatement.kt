package org.i3biscotti.kotlin2ast.ast.models






data class ExpressionDefinitionStatement(
    val expression: Expression,
    override val position: Position?
) : Statement()