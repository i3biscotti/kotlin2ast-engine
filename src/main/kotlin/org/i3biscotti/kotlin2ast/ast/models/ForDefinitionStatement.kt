package org.i3biscotti.kotlin2ast.ast.models







data class ForDefinitionStatement(
    val forCondition: ForCondition,
    val statements: List<Statement>,
    override val position: Position?
) : Statement()
