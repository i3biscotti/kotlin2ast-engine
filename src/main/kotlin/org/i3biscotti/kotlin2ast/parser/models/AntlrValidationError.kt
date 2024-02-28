package org.i3biscotti.kotlin2ast.parser.models

import org.i3biscotti.kotlin2ast.ast.models.Point

data class AntlrValidationError(val message: String,override val position: Point?) : LangError()
