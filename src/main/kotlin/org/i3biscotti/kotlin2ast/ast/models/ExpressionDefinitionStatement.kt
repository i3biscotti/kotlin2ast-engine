package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ExpressionDefinitionStatement")
data class ExpressionDefinitionStatement(
    val expression: Expression,
    override val position: Position?
) : Statement()