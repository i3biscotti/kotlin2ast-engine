package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class VarTypeMismatchError(
    val varName: String,
    val currentType: String,
    val expressionType: String,
    position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Variable $varName is of type $currentType, but the expression is of type $expressionType"
}