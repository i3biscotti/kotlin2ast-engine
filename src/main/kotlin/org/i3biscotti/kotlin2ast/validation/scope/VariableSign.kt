package org.i3biscotti.kotlin2ast.validation.scope

import org.i3biscotti.kotlin2ast.ast.models.VariableValueType

class VariableSign(
    val name: String,
    val type: VariableValueType,
    val isMutable: Boolean
) : ScopeObject()
