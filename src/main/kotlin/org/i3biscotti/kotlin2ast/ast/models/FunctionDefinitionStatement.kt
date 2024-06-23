package org.i3biscotti.kotlin2ast.ast.models


data class FunctionDefinitionStatement(
    val name: String,
    val parameters: List<Parameter>,
    val returnType: VariableValueType?,
    val statements: List<Statement>,
    override val position: Position?
) : Statement()