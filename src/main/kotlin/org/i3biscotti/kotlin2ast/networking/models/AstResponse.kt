package org.i3biscotti.kotlin2ast.networking.models

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import org.i3biscotti.kotlin2ast.ast.models.Node

@Serializable
data class AstResponse(@Polymorphic val root: Node)