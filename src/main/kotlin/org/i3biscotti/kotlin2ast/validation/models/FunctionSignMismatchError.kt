package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class FunctionSignMismatchError(
    val functionName: String,
    val expectedSign: String,
    val actualSign: String,
    position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Function $functionName has sign $actualSign, but expected $expectedSign"
}