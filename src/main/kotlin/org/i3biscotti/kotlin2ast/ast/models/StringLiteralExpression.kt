package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("StringLit")
data class StringLiteralExpression(
    val value: String,
    override val position: Position?
) : Expression()