package org.i3biscotti.kotlin2ast.parser.models

import kotlinx.serialization.Serializable
import org.i3biscotti.kotlin2ast.ast.models.Point

@Serializable
abstract class LangError{
    abstract val position: Point?
}
