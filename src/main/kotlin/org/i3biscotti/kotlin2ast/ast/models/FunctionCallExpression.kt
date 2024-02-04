package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("FunctionCallExpression")
data class FunctionCallExpression(
    val name: String,
    val parameters: List<Expression>,
    override val position: Position?
) : Expression()