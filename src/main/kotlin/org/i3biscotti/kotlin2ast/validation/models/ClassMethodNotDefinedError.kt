package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class ClassMethodNotDefinedError (
    val className: String,
    val methodName: String,
    override val position: Point
) : AstValidationError(position){
    override val message: String
        get() = "Method $methodName is not defined in class $className"
}