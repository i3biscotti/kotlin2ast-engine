package org.i3biscotti.kotlin2ast.validation.scope

import org.i3biscotti.kotlin2ast.ast.models.VariableValueType

data class ParamSign(val name: String, val type: VariableValueType)