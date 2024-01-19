package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ClassDefinitionStatement")
data class ClassDefinitionStatement(
    val name: String,
    val properties: List<PropertyDeclaration>,
    val constructors: List<ConstructorDefinitionStatement>,
    val methods: List<FunctionDefinitionStatement>,
    override val position: Position?
) : Statement()