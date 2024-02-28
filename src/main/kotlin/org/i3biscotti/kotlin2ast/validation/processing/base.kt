package org.i3biscotti.kotlin2ast.validation.processing


import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.validation.scope.ScopeContext

typealias ProcessOperationCallback = (Node, ScopeContext) -> Unit

inline fun < reified T : Node> Node.specificProcess(
    crossinline operation: (T, ScopeContext) -> Unit,
) {
    process(
        { node, nodeScope ->
            if (node is T) {
                operation(node, nodeScope)
            }
        },
        ScopeContext.rootScope
    )
}



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

    for (line in lines) {
        line.process(operation, scope)
    }
}