package org.i3biscotti.kotlin2ast.validation

import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.validation.models.*
import org.i3biscotti.kotlin2ast.validation.processing.*
import org.i3biscotti.kotlin2ast.validation.scope.*

class ProgramValidator(private val root: ProgramFile) {
    private var errors = mutableListOf<AstValidationError>()
    private val nodeProcessor = NodeProcessor(root)

    init {
        setupVariablesValidator()
        setupClassValidator()
        setupFunctionValidator()
        setupIfValidator()
        setupForValidator()
        getWhileErrors()
    }

    private fun checkGlobalErrors(): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()

        val hasMainFunction = root.lines.filterIsInstance<FunctionDefinitionStatement>()
            .any { it.name == "main" && it.returnType == VariableValueType.VOID }

        if (!hasMainFunction) {
            errors.add(MissingMainFunctionError(Point(0, 0)))
        }

        return errors
    }

    private fun setupVariablesValidator() {

        nodeProcessor.addProcess(VariableDeclarationStatement::class) { node, scope ->
            errors.addAll(validateVarDeclaration(node, scope))
        }

        nodeProcessor.addProcess(VarReferenceExpression::class) { node, scope ->
            if (scope.read<VariableSign>(node.name) == null) {
                errors.add(VarNotDeclaredError(node.name, node.position!!.start))
            }
        }

        nodeProcessor.addProcess(AssignmentStatement::class) { node, scope ->
            errors.addAll(validateAssignment(node, scope))
        }

    }

    private fun validateVarDeclaration(
        node: VariableDeclarationStatement, scope: ScopeContext
    ): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()

        if (scope.read<VariableSign>(node.name) != null) {
            errors.add(VarAlreadyDeclaredError(node.name, node.position!!.start))
        }

        if (node.value != null) {
            lateinit var  expressionType : VariableValueType

            try {
                expressionType = extractType(scope, node.value)
            } catch (e: Exception) {
                expressionType = VariableValueType.DYNAMIC
            }

            if (node.valueType != null && expressionType != node.valueType) {
                errors.add(
                    VarTypeMismatchError(
                        node.name, node.valueType.name, expressionType.name, node.value.position!!.start
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

    private fun setupClassValidator() {

        nodeProcessor.addProcess(ClassDefinitionStatement::class) { node, scope ->
            errors.addAll(validateClass(node, scope))
        }

        nodeProcessor.addProcess(ObjectPropertyAssignmentStatement::class) { node, scope ->
            errors.addAll(validatePropertyAssignment(node, scope))
        }

        nodeProcessor.addProcess(ObjectPropertyReferenceExpression::class) { node, scope ->
            errors.addAll(validatePropertyReference(node, scope))
        }

        nodeProcessor.addProcess(ObjectMethodCallExpression::class) { node, scope ->
            errors.addAll(validateMethod(node, scope))
        }

    }

    private fun validateMethod(node: ObjectMethodCallExpression, scope: ScopeContext): List<AstValidationError> {
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
                    node.objectName, node.position!!.start
                ),
            )
        }

        return errors
    }

    private fun validatePropertyReference(
        node: ObjectPropertyReferenceExpression, scope: ScopeContext
    ): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()
        val variable = scope.read<VariableSign>(node.objectName)

        if (variable != null) {
            val className = variable.type.name
            val classSign = scope.read<ClassSign>(className)

            val hasProperty = classSign!!.properties.any { it.name == node.propertyName }

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

    private fun validatePropertyAssignment(
        node: ObjectPropertyAssignmentStatement, scope: ScopeContext
    ): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()

        val variable = scope.read<VariableSign>(node.objectName)

        if (variable != null) {
            val className = variable.type.name
            val classSign = scope.read<ClassSign>(className)

            val hasProperty = classSign!!.properties.any { it.name == node.propertyName }

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

    private fun validateClass(node: ClassDefinitionStatement, scope: ScopeContext): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()

        if (scope.read<ClassSign>(node.name) != null) {
            errors.add(ClassAlreadyDeclaredError(node.name, node.position!!.start))
        }

        return errors
    }

    private fun setupFunctionValidator() {
        nodeProcessor.addProcess(FunctionDefinitionStatement::class) { node, scope ->
            errors.addAll(validateFunction(node, scope))
        }

        nodeProcessor.addProcess(FunctionCallExpression::class) { node, scope ->
            errors.addAll(validateFunctionCall(node, scope))
        }

    }

    private fun validateFunctionCall(node: FunctionCallExpression, scope: ScopeContext): List<AstValidationError> {
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

    private fun validateFunction(node: FunctionDefinitionStatement, scope: ScopeContext): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()

        val fSign = scope.read<FunctionSign>(node.name)
        val hasReturn = node.statements.filterIsInstance<ReturnStatement>().isNotEmpty()

        if (fSign != null && fSign.position != node.position!!.start) {
            errors.add(
                FunctionAlreadyDefinedError(
                    node.name,
                    node.position.start,
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
            val returnType = getFunctionReturnType(node, scope)

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

    private fun getFunctionReturnType(
        fDef: FunctionDefinitionStatement, scope: ScopeContext
    ): VariableValueType {
        val fScope = scope.wrap()

        fScope.declaredVariables.putAll(fDef.parameters.map {
            it.name to VariableSign(
                it.name, it.valueType, true, fDef.position!!.start
            )
        })

        fScope.declaredVariables.putAll(fDef.statements.filterIsInstance<VariableDeclarationStatement>().map {
            it.name to VariableSign(
                it.name, it.valueType ?: extractType(fScope, it.value!!), true, it.position!!.start
            )
        })
        val returnStm = fDef.statements.last { it is ReturnStatement } as ReturnStatement
        val returnType = extractType(fScope, returnStm.value!!)

        return returnType

    }

    private fun setupIfValidator() {

        nodeProcessor.addProcess(IfDefinitionStatement::class) { node, scope ->
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

    }

    private fun setupForValidator() {

        nodeProcessor.addProcess(ForDefinitionStatement::class) { node, scope ->
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

    }

    private fun getWhileErrors(): List<AstValidationError> {
        val errors = mutableListOf<AstValidationError>()

        nodeProcessor.addProcess(WhileDefinitionStatement::class) { node, scope ->
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

    fun startValidation(): List<AstValidationError> {
        errors = mutableListOf()
        errors.addAll(checkGlobalErrors())

        nodeProcessor.process()

        return errors
    }
}

