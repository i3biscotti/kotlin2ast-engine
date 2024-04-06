package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class FunctionReturnTypeMismatchError(
    val functionName: String,
    val expectedType: String,
    val actualType: String,
    position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Function $functionName has return type $actualType, but expected $expectedType"
}