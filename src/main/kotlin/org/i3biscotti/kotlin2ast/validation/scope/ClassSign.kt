package org.i3biscotti.kotlin2ast.validation.scope

import org.i3biscotti.kotlin2ast.ast.models.Point

class ClassSign(
    val name: String,
    val properties: List<VariableSign>,
    val methods: List<FunctionSign>,
    val membersEncapsulationRegistry: Map<String, Boolean>,
    override val position: Point
) : ScopeObject()