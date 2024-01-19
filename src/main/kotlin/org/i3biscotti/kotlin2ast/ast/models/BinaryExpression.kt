package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.Serializable

//task 2
@Serializable
sealed class BinaryExpression : Expression() {
    abstract val left: Expression
    abstract val right: Expression
    abstract override val position: Position?
}