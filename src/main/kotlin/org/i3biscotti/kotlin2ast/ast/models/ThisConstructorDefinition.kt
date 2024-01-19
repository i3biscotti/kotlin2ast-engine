package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ThisConstructorDefinition")
data class ThisConstructorDefinition(
    val parameters: List<Expression>,
    override val position: Position?
) : Node