package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("VarDeclarationStatement")
data class VariableDeclarationStatement(
    val varType: VariableType,
    val name: String,
    val valueType: VariableValueType?,
    val value: Expression?,
    override val position: Position?
) : Statement()