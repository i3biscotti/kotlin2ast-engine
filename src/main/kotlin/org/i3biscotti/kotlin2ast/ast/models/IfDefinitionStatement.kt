package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("IfDefinitionStatement")

data class IfDefinitionStatement(
    val ifBlock : Statement,
    val elseIfBlock : List<Statement>?,
    val elseBlock : Statement?,
    override val position: Position?
) : Statement()