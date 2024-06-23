package org.i3biscotti.kotlin2ast.ast.models





data class IntLiteralExpression(val value: String, override val position: Position?) : Expression()