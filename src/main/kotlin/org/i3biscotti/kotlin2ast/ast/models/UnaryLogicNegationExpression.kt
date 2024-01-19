package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("UnaryLogicNegationExpression")
data class UnaryLogicNegationExpression(
    override val position: Position?,
    override val value: Expression
) : UnaryExpression()