package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ParenthesisExpression")
data class ParenthesisExpression(
    val value: Expression,
    override val position: Position?
) : Expression()