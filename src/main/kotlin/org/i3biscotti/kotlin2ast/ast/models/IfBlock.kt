package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("IfBlock")
data class IfBlock(
    val condition: Expression?,
    val statements: List<Statement>,
    val blockType: BlockType,
    override val position: Position?
): Node