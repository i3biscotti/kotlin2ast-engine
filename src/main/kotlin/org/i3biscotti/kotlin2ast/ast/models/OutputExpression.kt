package org.i3biscotti.kotlin2ast.ast.models

data class OutputExpression(val value: Expression, override val position: Position?) : Expression()