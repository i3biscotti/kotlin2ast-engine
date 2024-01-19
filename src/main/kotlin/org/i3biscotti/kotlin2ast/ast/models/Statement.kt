package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Statement : Node {
    abstract override val position: Position?
}