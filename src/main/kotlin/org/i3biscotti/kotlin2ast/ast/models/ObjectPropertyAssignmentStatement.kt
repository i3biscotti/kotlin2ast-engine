package org.i3biscotti.kotlin2ast.ast.models






data class ObjectPropertyAssignmentStatement(
    val objectName: String,
    val propertyName: String,
    val value: Expression,
    override val position: Position?
) : Statement()