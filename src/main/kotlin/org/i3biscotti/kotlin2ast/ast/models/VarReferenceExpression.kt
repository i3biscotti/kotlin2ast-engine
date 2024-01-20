package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.*


@Serializable
@SerialName("VarReferenceExpression")
data class VarReferenceExpression(
    val name: String,
    override val position: Position?
) : Expression()