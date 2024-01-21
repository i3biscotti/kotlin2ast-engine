package org.i3biscotti.kotlin2ast.ast.models

data class ReturnStatement(val value : Expression?, override val position: Position?) : Statement()