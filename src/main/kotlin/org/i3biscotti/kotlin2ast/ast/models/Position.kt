package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.Serializable

@Serializable
data class Position(val start: Point, val end: Point)
