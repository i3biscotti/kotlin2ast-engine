package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ObjectPropertyAssignmentStatement")
data class ObjectPropertyAssignmentStatement(
    val objectName: String,
    val propertyName: String,
    val value: Expression,
    override val position: Position?
) : Statement()