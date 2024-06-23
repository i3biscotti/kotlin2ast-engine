package org.i3biscotti.kotlin2ast.ast.models




sealed class UnaryExpression : Expression() {
    abstract val value: Expression
    abstract override val position: Position?
}