package org.i3biscotti.kotlin2ast.ast.models




data class ClassDefinitionStatement(
    val isPrivate: Boolean,
    val name: String,
    val properties: List<PropertyDeclaration>,
    val constructors: List<ConstructorDefinitionStatement>,
    val methods: List<FunctionDefinitionStatement>,
    val parentClassType: VariableValueType?,
    override val position: Position?
) : Statement()