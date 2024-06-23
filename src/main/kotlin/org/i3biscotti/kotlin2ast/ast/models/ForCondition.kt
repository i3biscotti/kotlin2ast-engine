package org.i3biscotti.kotlin2ast.ast.models


//task5

sealed class ForCondition : Node {
    abstract override val position: Position?
}