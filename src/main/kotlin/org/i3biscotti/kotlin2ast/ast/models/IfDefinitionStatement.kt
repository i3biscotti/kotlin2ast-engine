package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("IfDefinitionStatement")
data class IfDefinitionStatement(
    val ifBlock : IfBlock,
    val elseIfBlock : List<IfBlock>?,
    val elseBlock : IfBlock?,
    override val position: Position?
) : Statement()