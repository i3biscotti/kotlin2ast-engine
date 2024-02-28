package org.i3biscotti.kotlin2ast.validation

import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.validation.models.*
import org.i3biscotti.kotlin2ast.validation.processing.specificProcess
import org.i3biscotti.kotlin2ast.validation.scope.*

fun ProgramFile.validate(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    errors.addAll(checkGlobalErrors())
    errors.addAll(getVariableErrors())
    errors.addAll(getClassErrors())
    errors.addAll(getFunctionErrors())
    errors.addAll(getIfErrors())
    errors.addAll(getForErrors())
    errors.addAll(getWhileErrors())

    return errors
}

private fun ProgramFile.checkGlobalErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    val hasMainFunction = this.lines
        .filterIsInstance<FunctionDefinitionStatement>()
        .any { it.name == "main" && it.returnType == VariableValueType.VOID }

    if (!hasMainFunction) {
        errors.add(MissingMainFunctionError(Point(0, 0)))
    }

    return errors
}

private fun ProgramFile.getVariableErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    specificProcess<VariableDeclarationStatement> { node, scope ->
        errors.addAll(validateVarDeclaration(node, scope))
    }

    specificProcess<VarReferenceExpression> { node, scope ->
        if (scope.read<VariableSign>(node.name) == null) {
            errors.add(VarNotDeclaredError(node.name, node.position!!.start))
        }
    }

    specificProcess<AssignmentStatement> { node, scope ->
        errors.addAll(validateAssignment(node, scope))
    }

    return errors
}

private fun validateVarDeclaration(node: VariableDeclarationStatement, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    if (scope.read<VariableSign>(node.name) != null) {
        errors.add(VarAlreadyDeclaredError(node.name, node.position!!.start))
    }

    if (node.value != null) {
        val expressionType = extractType(scope, node.value)

        if (node.valueType != null && expressionType != node.valueType) {
            errors.add(
                VarTypeMismatchError(
                    node.name,
                    node.valueType.name,
                    expressionType.name,
                    node.value.position!!.start
                ),
            )
        }
    } else {
        errors.add(VarValueNotAssigned(node.name, node.position!!.start))
    }

    return errors
}

private fun validateAssignment(node: AssignmentStatement, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    val varSign = scope.read<VariableSign>(node.name)
    val expressionType = extractType(scope, node.value)

    if (varSign == null) {
        errors.add(VarNotDeclaredError(node.name, node.position!!.start))

        return errors
    }

    if (!varSign.isMutable) {
        errors.add(VarImmutableError(node.name, node.position!!.start))
    } else if (varSign.type != expressionType) {
        errors.add(
            VarTypeMismatchError(
                node.name,
                varSign.type.name,
                expressionType.name,
                node.value.position!!.start,
            ),
        )
    }

    return errors
}

private fun ProgramFile.getClassErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    specificProcess<ClassDefinitionStatement> { node, scope ->
        errors.addAll(validateClass(node, scope))
    }

    specificProcess<ObjectPropertyAssignmentStatement> { node, scope ->
        errors.addAll(validatePropertyAssignment(node, scope))
    }

    specificProcess<ObjectPropertyReferenceExpression> { node, scope ->
        errors.addAll(validatePropertyReference(node, scope))
    }

    specificProcess<ObjectMethodCallExpression> { node, scope ->
        errors.addAll(validateMethod(node, scope))
    }

    return errors
}

fun validateMethod(node: ObjectMethodCallExpression, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()
    val variable = scope.read<VariableSign>(node.objectName)

    if (variable != null) {
        val className = variable.type.name
        val classSign = scope.read<ClassSign>(className)

        val method = classSign!!.methods.firstOrNull { it.name == node.methodName }


        if (method == null) {
            errors.add(
                ClassMethodNotDefinedError(
                    className,
                    node.methodName,
                    node.position!!.start,
                ),
            )
        } else {
            val methodParams = method.parameters.map { it.type.name }
            val callParams = node.params.map { extractType(scope, it).name }

            if (methodParams != callParams) {
                errors.add(
                    FunctionSignMismatchError(
                        "$className.${node.methodName}",
                        "(${methodParams.joinToString(", ")})",
                        "(${callParams.joinToString(", ")})",
                        node.position!!.start,
                    ),
                )
            }
        }
    } else {
        errors.add(
            VarNotDeclaredError(
                node.objectName,
                node.position!!.start,
            ),
        )
    }

    return errors
}

fun validatePropertyReference(node: ObjectPropertyReferenceExpression, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()
    val variable = scope.read<VariableSign>(node.objectName)

    if (variable != null) {
        val className = variable.type.name
        val classSign = scope.read<ClassSign>(className)

        val hasProperty =
            classSign!!.properties.any { it.name == node.propertyName }

        if (!hasProperty) {
            errors.add(
                ClassPropertyNotDefinedError(
                    className,
                    node.propertyName,
                    node.position!!.start,
                ),
            )
        }
    } else {
        errors.add(
            VarNotDeclaredError(node.objectName, node.position!!.start),
        )
    }

    return errors
}

fun validatePropertyAssignment(node: ObjectPropertyAssignmentStatement, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    val variable = scope.read<VariableSign>(node.objectName)

    if (variable != null) {
        val className = variable.type.name
        val classSign = scope.read<ClassSign>(className)

        val hasProperty =
            classSign!!.properties.any { it.name == node.propertyName }

        if (!hasProperty) {
            errors.add(
                ClassPropertyNotDefinedError(
                    className,
                    node.propertyName,
                    node.position!!.start,
                ),
            )
        }
    } else {
        errors.add(
            VarNotDeclaredError(node.objectName, node.position!!.start),
        )
    }

    return errors
}

fun validateClass(node: ClassDefinitionStatement, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    if (scope.read<ClassSign>(node.name) != null) {
        errors.add(ClassAlreadyDeclaredError(node.name, node.position!!.start))
    }

    return errors
}

private fun ProgramFile.getFunctionErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    specificProcess<FunctionDefinitionStatement> { node, scope ->
        errors.addAll(validateFunction(node, scope))
    }

    specificProcess<FunctionCallExpression> { node, scope ->
        errors.addAll(validateFunctionCall(node, scope))
    }

    return errors
}

fun validateFunctionCall(node: FunctionCallExpression, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    val function = scope.read<FunctionSign>(node.name)

    if (function != null) {
        val functionParams = function.parameters.map { it.type.name }
        val callParams = node.parameters.map { extractType(scope, it).name }

        if (functionParams != callParams) {
            errors.add(
                FunctionSignMismatchError(
                    node.name,
                    "(${functionParams.joinToString(", ")})",
                    "(${callParams.joinToString(", ")})",
                    node.position!!.start,
                ),
            )
        }
    } else {
        errors.add(
            FunctionNotDefinedError(
                node.name,
                node.position!!.start,
            ),
        )
    }

    return errors
}

fun validateFunction(node: FunctionDefinitionStatement, scope: ScopeContext): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    val hasReturn = node.statements.filterIsInstance<ReturnStatement>().isNotEmpty()

    if (scope.read<FunctionSign>(node.name) != null) {
        errors.add(
            FunctionAlreadyDefinedError(
                node.name,
                node.position!!.start,
            ),
        )
    } else if (!hasReturn && node.returnType != VariableValueType.VOID) {
        errors.add(
            FunctionMissingReturnError(
                node.name,
                node.position!!.start,
            ),
        )
    } else if (hasReturn && node.returnType == VariableValueType.VOID) {
        errors.add(
            FunctionReturnNotAllowedError(
                node.name,
                node.position!!.start,
            ),
        )
    } else if (hasReturn && node.returnType != null) {
        val returnType = extractType(
            scope,
            node.statements.filterIsInstance<ReturnStatement>().last().value!!,
        )

        if (returnType != node.returnType) {
            errors.add(
                FunctionReturnTypeMismatchError(
                    node.name,
                    node.returnType.name,
                    returnType.name,
                    node.position!!.start,
                ),
            )
        }
    }

    return errors
}

private fun ProgramFile.getIfErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    specificProcess<IfDefinitionStatement> { node, scope ->
        (listOf(node.ifBlock) + (node.elseIfBlock ?: emptyList())).forEach { ifBlock ->
            val conditionType = extractType(scope, ifBlock.condition!!)

            if (conditionType != VariableValueType.BOOLEAN) {
                errors.add(
                    ExpressionMismatchError(
                        VariableValueType.BOOLEAN.name,
                        conditionType.name,
                        ifBlock.position!!.start,
                    ),
                )
            }
        }

    }

    return errors
}

private fun ProgramFile.getForErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    specificProcess<ForDefinitionStatement> { node, scope ->
        TODO("Fix for condition before continue.")
        val variableType = extractType(scope, node.forCondition)

        if (variableType != VariableValueType.INT) {
            errors.add(
                ExpressionMismatchError(
                    VariableValueType.INT.name,
                    variableType.name,
                    node.forCondition.position!!.start,
                ),
            )
        }
    }

    return errors
}

private fun ProgramFile.getWhileErrors(): List<AstValidationError> {
    val errors = mutableListOf<AstValidationError>()

    specificProcess<WhileDefinitionStatement> { node, scope ->
        val conditionType = extractType(scope, node.whileCondition)

        if (conditionType != VariableValueType.BOOLEAN) {
            errors.add(
                ExpressionMismatchError(
                    VariableValueType.BOOLEAN.name,
                    conditionType.name,
                    node.whileCondition.position!!.start,
                ),
            )
        }
    }

    return errors
}