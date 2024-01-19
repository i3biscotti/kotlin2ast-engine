package org.i3biscotti.kotlin2ast.ast.models

import kotlinx.serialization.*

@Serializable
data class Point(val line: Int, val column: Int)
