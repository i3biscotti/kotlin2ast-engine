package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class ExpressionMismatchError(
    private val typeExpected: String,
    private val typeReceived: String,
    position: Point
) : AstValidationError(position) {
     override val message: String
        get() = "Expression type mismatch. Expected $typeExpected, received $typeReceived"
}