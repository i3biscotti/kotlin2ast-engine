package org.i3biscotti.kotlin2ast.validation.processing

import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.validation.scope.*

fun Statement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    operation(this, scope)

    when (this) {
        is VariableDeclarationStatement -> process(operation, scope)
        is IfDefinitionStatement -> process(operation, scope)
        is WhileDefinitionStatement -> process(operation, scope)
        is FunctionDefinitionStatement -> process(operation, scope)
        is ClassDefinitionStatement -> process(operation, scope)
        is ForDefinitionStatement -> process(operation, scope)
        is ExpressionDefinitionStatement -> this.expression.process(operation, scope)
        is ReturnStatement -> this.value?.process(operation, scope)
        is AssignmentStatement -> this.value.process(operation, scope)
        is ConstructorDefinitionStatement -> return
        is ObjectPropertyAssignmentStatement -> this.value.process(operation, scope)
        is AssignmentForStatement -> TODO()
        is ExpressionForStatement -> TODO()
        is VarDeclarationForStatement -> TODO()
    }
}

fun VariableDeclarationStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    value?.process(operation, scope)

    lateinit var varSign: VariableSign
    try {
        varSign = VariableSign(
            name,
            valueType ?: extractType(scope, value!!),
            varType == VariableType.variable,
            position!!.start
        )
    } catch (e: Exception) {
        varSign = VariableSign(
            name,
            VariableValueType.DYNAMIC,
            varType == VariableType.variable,
            position!!.start
        )
    }

    scope.declaredVariables[name] = varSign
}

fun IfDefinitionStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    (listOf(ifBlock) + (elseIfBlock ?: emptyList())).forEach { it.process(operation, scope) }
}

fun IfBlock.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    val scopeBlock = scope.wrap()
    condition?.process(operation, scope)
    statements.forEach { it.process(operation, scopeBlock) }
}

fun FunctionDefinitionStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    val functionScope = prepareNewScope(scope, statements)

    functionScope.declaredVariables.putAll(
        parameters.map {
            it.name to VariableSign(
                it.name,
                it.valueType,
                true,
                position!!.start
            )
        }
    )

    statements.forEach { it.process(operation, functionScope) }
}

fun ClassDefinitionStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    properties.forEach { it.process(operation, scope) }

    val classScope = prepareNewScope(scope, emptyList())

    classScope.declaredVariables.putAll(
        properties.map {
            it.name to VariableSign(
                it.name,
                it.valueType,
                true,
                position!!.start,
            )
        }
    )

    val methodSigns = methods.map { it.name to generateFunctionSign(it) }
    val constructorSigns = constructors.map { it.constructorName to generateConstructorFunctionSign(it) }
    classScope.declaredFunctions.putAll(methodSigns + constructorSigns)

    constructors.forEach { it.process(operation, scope) }
    properties.forEach { it.process(operation, scope) }
    methods.forEach { it.process(operation, scope) }
}

fun ConstructorDefinitionStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    val constructorScope = scope.wrap()

    body.forEach { it.process(operation, constructorScope) }
}

fun WhileDefinitionStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    whileCondition.process(operation, scope)

    val whileContext = scope.wrap()
    statements.forEach { it.process(operation, whileContext) }
}

fun ForDefinitionStatement.process(operation: ProcessOperationCallback, scope: ScopeContext) {
    forCondition.process(operation, scope)
}
