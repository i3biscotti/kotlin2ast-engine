package org.i3biscotti.kotlin2ast.ast.models






data class ThisConstructorDefinition(
    val parameters: List<Expression>,
    override val position: Position?
) : Node