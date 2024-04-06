package org.i3biscotti.kotlin2ast.ast.models

data class ObjectPropertyReferenceExpression(
    val objectName: String,
    val propertyName: String,
    override val position: Position?
) : Expression()