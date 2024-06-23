package org.i3biscotti.kotlin2ast.parser.models


import org.i3biscotti.kotlin2ast.ast.models.Point


abstract class LangError{
    abstract val position: Point?
}
