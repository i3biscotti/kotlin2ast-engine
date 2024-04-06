package org.i3biscotti.kotlin2ast.validation.scope

import org.i3biscotti.kotlin2ast.ast.models.Point

sealed class ScopeObject{
    abstract val position: Point
}