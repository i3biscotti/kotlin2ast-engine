package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class FunctionAlreadyDefinedError(
    private val functionName: String,
    position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Function $functionName is already defined"
}