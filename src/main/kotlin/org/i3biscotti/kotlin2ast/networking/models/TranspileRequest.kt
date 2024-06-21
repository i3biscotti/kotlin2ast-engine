package org.i3biscotti.kotlin2ast.networking.models

import kotlinx.serialization.Serializable
import org.i3biscotti.kotlin2ast.ast.models.ProgramFile

@Serializable
data class TranspileRequest(val ast: ProgramFile)