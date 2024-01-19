package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Parameter")
data class Parameter(
    val paramType: ParameterType,
    val name: String,
    val valueType: VariableValueType,
    override val position: Position?
) : Node