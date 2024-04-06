package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class MissingMainFunctionError(position: Point) : AstValidationError(position) {
    override val message: String
        get() = "Missing main function"
}