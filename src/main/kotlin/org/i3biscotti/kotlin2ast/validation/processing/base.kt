package org.i3biscotti.kotlin2ast.validation.processing


import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.validation.scope.ScopeContext
import org.i3biscotti.kotlin2ast.validation.scope.prepareNewScope

typealias ProcessOperationCallback = (Node, ScopeContext) -> Unit

fun Node.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    when (this) {
        is ProgramFile -> process(operation, ScopeContext.rootScope)
        is Statement -> process(operation, scope)
        is Expression -> process(operation, scope)
        is IfBlock -> process(operation, scope)
    }
}

fun ProgramFile.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    operation(this, scope)

    prepareNewScope(scope, lines).let { newScope ->
        lines.forEach { it.process(operation, newScope) }
    }
}