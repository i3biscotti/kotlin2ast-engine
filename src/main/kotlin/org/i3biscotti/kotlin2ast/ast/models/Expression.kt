package org.i3biscotti.kotlin2ast.ast.models




sealed class Expression : Node {
    abstract override val position: Position?
}