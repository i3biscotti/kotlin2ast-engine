package org.i3biscotti.kotlin2ast.ast.models




sealed class Statement : Node {
    abstract override val position: Position?
}