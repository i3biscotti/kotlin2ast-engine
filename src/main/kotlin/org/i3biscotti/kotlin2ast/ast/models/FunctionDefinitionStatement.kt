package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("FunctionDefinitionStatement")
data class FunctionDefinitionStatement(
    val name: String,
    val parameters: List<Parameter>,
    val returnType: VariableValueType?,
    val statements: List<Statement>,
    override val position: Position?
) : Statement()