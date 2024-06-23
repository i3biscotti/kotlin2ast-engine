package org.i3biscotti.kotlin2ast.ast.models



//task 2

sealed class BinaryExpression : Expression() {
    abstract val left: Expression
    abstract val right: Expression
    abstract override val position: Position?
}