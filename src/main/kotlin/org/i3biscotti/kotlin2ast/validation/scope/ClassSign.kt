package org.i3biscotti.kotlin2ast.validation.scope

class ClassSign(
    val name: String,
    val properties: List<VariableSign>,
    val methods: List<FunctionSign>,
    val membersEncapsulationRegistry: Map<String, Boolean>
) : ScopeObject()