package org.i3biscotti.kotlin2ast.ast.models






data class IfBlock(
    val condition: Expression?,
    val statements: List<Statement>,
    val blockType: BlockType,
    override val position: Position?
): Node