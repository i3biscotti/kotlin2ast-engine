package org.i3biscotti.kotlin2ast.ast.models






data class Parameter(
    val paramType: ParameterType,
    val name: String,
    val valueType: VariableValueType,
    override val position: Position?
) : Node