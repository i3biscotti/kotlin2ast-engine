package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.*

@Serializable
@SerialName("ProgramFile")
data class ProgramFile(
    val lines: List<Statement>,
    override val position: Position?
) : Node