package org.i3biscotti.kotlin2ast.ast.models

sealed class ForInitOrIncrementStatement : Statement() {
    abstract override val position: Position?
}