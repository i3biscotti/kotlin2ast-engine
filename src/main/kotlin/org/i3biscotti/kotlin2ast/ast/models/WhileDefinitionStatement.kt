package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("WhileDefinitionStatement")
data class WhileDefinitionStatement(
    val whileCondition: Expression,
    val statements: List<Statement>,
    override val position: Position?
) : Statement()