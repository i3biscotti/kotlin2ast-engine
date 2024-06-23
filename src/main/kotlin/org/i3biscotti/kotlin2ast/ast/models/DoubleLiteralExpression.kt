package org.i3biscotti.kotlin2ast.ast.models






data class DoubleLiteralExpression(val value: String, override val position: Position?) : Expression()