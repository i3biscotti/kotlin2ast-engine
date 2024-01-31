package org.i3biscotti.kotlin2ast.ast

import org.i3biscotti.kotlin2ast.ast.models.*

typealias ProcessOperationCallback = (Node) -> Unit

fun Node.process(operation: ProcessOperationCallback) {
    when (this) {
        is ProgramFile -> this.process(operation)
        is Statement -> this.process(operation)
        is Expression -> this.process(operation)
    }
}

fun ProgramFile.process(operation: ProcessOperationCallback) {
    operation(this)

    for (line in lines) {
        line.process(operation)
    }
}

fun Statement.process(operation: ProcessOperationCallback) {
    operation(this)

    when (this) {
        is VarDeclarationStatement -> this.value.process(operation)
        is AssignmentStatement -> this.value.process(operation)
        is ExpressionDefinitionStatement -> this.expression.process(operation)
        is FunctionDefinitionStatement -> process(operation)
        is ClassDefinitionStatement -> process(operation)
        is ReturnStatement -> this.value?.process(operation)
        is ConstructorDefinitionStatement -> return
        is ObjectPropertyAssignmentStatement -> this.value.process(operation)
    }
}

fun FunctionDefinitionStatement.process(operation: ProcessOperationCallback) {
    statements.forEach { it.process(operation) }
}

fun ClassDefinitionStatement.process(operation: ProcessOperationCallback) {
    constructors.forEach { it.process(operation) }
    properties.forEach(operation)
    methods.forEach { it.process(operation) }
}

fun Expression.process(operation: ProcessOperationCallback) {
    operation(this)

    when (this) {
        is BinaryExpression -> process(operation)
        is UnaryExpression -> this.value.process(operation)
        is ParenthesisExpression -> this.value.process(operation)
        is FunctionCallExpression -> this.process(operation)
        else -> return
    }
}

fun BinaryExpression.process(operation: ProcessOperationCallback) {
    left.process(operation)
    right.process(operation)
}

fun FunctionCallExpression.process(operation: ProcessOperationCallback) {
    parameters.forEach(operation)
}
