package org.i3biscotti.kotlin2ast.validation.scope

import org.i3biscotti.kotlin2ast.ast.models.Point
import org.i3biscotti.kotlin2ast.ast.models.VariableValueType

class FunctionSign(
    val name: String,
    val returnType: VariableValueType,
    val parameters: List<ParamSign>,
    override val position: Point
) : ScopeObject()