package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class ClassAlreadyDeclaredError (
    val className: String,
    override val position: Point
) : AstValidationError(position){
    override val message: String
        get() = "Class $className is already declared"
}