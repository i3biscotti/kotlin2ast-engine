package org.i3biscotti.kotlin2ast.ast.models

data class VarDeclarationForStatement(
    val varType: VariableType,
    val name: String,
    val valueType: VariableValueType?,
    val value: Expression,
    override val position: Position?
) : ForInitOrIncrementStatement()