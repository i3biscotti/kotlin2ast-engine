package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ForDefinitionStatement")

data class ForDefinitionStatement(
    val forCondition: ForCondition,
    val statements: List<Statement>,
    override val position: Position?
) : Statement()
