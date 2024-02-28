package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class VarValueNotAssigned(
    val varName: String,
    override val position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Variable $varName is not assigned"
}