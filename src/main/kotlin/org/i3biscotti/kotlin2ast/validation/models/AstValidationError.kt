package org.i3biscotti.kotlin2ast.validation.models

import org.i3biscotti.kotlin2ast.ast.models.Point
import org.i3biscotti.kotlin2ast.parser.models.*

abstract class AstValidationError(override val position: Point?) : LangError(){
    abstract val message: String
}
