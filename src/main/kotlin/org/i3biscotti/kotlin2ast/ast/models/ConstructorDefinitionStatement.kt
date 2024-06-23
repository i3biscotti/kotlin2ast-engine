package org.i3biscotti.kotlin2ast.ast.models






data class ConstructorDefinitionStatement(
    val className: String,
    val constructorName: String,
    val parameters: List<Parameter>,
    val body: List<Statement>,
    val thisConstructor: ThisConstructorDefinition?,
    override val position: Position?
) : Statement()