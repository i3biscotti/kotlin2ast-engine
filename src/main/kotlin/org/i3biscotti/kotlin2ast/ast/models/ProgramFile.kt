package org.i3biscotti.kotlin2ast.ast.models





data class ProgramFile(
    val lines: List<Statement>,
    override val position: Position?
) : Node