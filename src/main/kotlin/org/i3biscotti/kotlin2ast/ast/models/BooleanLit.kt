package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BooleanLit")
data class BooleanLit(val value: String, override val position: Position?) : Expression()