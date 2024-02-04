package org.i3biscotti.kotlin2ast.ast.models

data class ElseIfBlock(
    val condition: org.i3biscotti.kotlin2ast.ast.models.Expression?,
    val statement: List<Statement>,
    val blockType: BlockType,
    override val position: Position?
): Statement()