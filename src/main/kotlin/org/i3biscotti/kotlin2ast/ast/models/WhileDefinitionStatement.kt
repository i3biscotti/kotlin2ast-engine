package org.i3biscotti.kotlin2ast.ast.models






data class WhileDefinitionStatement(
    val whileCondition: Expression,
    val statements: List<Statement>,
    override val position: Position?
) : Statement()