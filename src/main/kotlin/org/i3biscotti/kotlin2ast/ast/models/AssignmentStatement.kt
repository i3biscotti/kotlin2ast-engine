package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AssignmentStatement")
data class AssignmentStatement(
    val name: String,
    val value: Expression,
    override val position: Position?
) : Statement()