package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ConstructorDefinitionStatement")
data class ConstructorDefinitionStatement(
    val className: String,
    val constructorName: String,
    val parameters: List<Parameter>,
    val body: List<Statement>,
    val thisConstructor: ThisConstructorDefinition?,
    override val position: Position?
) : Statement()