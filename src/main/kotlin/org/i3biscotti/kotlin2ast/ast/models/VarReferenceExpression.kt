package org.i3biscotti.kotlin2ast.ast.models






data class VarReferenceExpression(
    val name: String,
    override val position: Position?
) : Expression()