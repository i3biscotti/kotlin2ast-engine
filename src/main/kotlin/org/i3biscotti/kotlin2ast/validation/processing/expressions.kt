package org.i3biscotti.kotlin2ast.validation.processing

import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.validation.scope.*


fun Expression.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    operation(this, scope)

    when (this) {
        is BinaryExpression -> process(operation, scope)
        is UnaryExpression -> this.value.process(operation, scope)
        is ParenthesisExpression -> this.value.process(operation, scope)
        is FunctionCallExpression -> this.process(operation, scope)
        is ListOfExpression -> this.process(operation, scope)
        else -> return
    }
}

fun BinaryExpression.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    left.process(operation, scope)
    right.process(operation, scope)
}

fun ListOfExpression.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    items.forEach { it.process(operation, scope) }
}

fun FunctionCallExpression.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    parameters.forEach { it.process(operation, scope) }
}
