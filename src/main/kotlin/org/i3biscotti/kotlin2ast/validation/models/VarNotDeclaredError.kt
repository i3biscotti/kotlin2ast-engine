package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class VarNotDeclaredError(
    val varName: String,
    position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Variable $varName is not declared"
}
