package org.i3biscotti.kotlin2ast.ast.models

interface Node {
    val position: Position?

    val nodeType: String
        get() = javaClass.kotlin.simpleName!!
}
