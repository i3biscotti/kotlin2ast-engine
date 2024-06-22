package org.i3biscotti.kotlin2ast.ast.models
import kotlinx.serialization.Serializable

//task5
@Serializable
sealed class ForCondition : Node {
    abstract override val position: Position?
}