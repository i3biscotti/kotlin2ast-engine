package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ObjectMethodCallExpression")
data class ObjectMethodCallExpression(
    val objectName: String,
    val methodName: String,
    val params: List<Expression>,
    override val position: Position?
) : Expression()