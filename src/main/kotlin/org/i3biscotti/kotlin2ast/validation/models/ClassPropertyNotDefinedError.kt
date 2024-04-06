package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point

class ClassPropertyNotDefinedError(
    val className: String,
    val propertyName: String,
    override val position: Point
) : AstValidationError(position) {
    override val message: String
        get() = "Property $propertyName is not defined in class $className"
}