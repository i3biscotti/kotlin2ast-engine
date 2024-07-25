package org.i3biscotti.kotlin2ast.validation.scope

import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.parser.models.LangError

internal fun prepareNewScope(scope: ScopeContext, statementsBlock: List<Statement>): ScopeContext {

    val classes = statementsBlock
        .filterIsInstance<ClassDefinitionStatement>()
        .map { generateClassSign(scope, it) }

    val functions = statementsBlock.filterIsInstance<FunctionDefinitionStatement>()
        .map { statement ->
            FunctionSign(
                statement.name,
                statement.returnType ?: VariableValueType.VOID,
                statement.parameters.map { ParamSign(it.name, it.valueType) },
                statement.position!!.start
            )
        }

    val declaredClasses = mutableMapOf<String, ClassSign>()
    declaredClasses.putAll(classes.map { it.name to it })

    val declaredFunctions = mutableMapOf<String, FunctionSign>()
    declaredFunctions.putAll(functions.map { it.name to it })

    return scope.wrap(
        declaredClasses = declaredClasses,
        declaredFunctions = declaredFunctions
    )
}

fun generateFunctionSign(statement: FunctionDefinitionStatement): FunctionSign {
    return FunctionSign(
        statement.name,
        statement.returnType ?: VariableValueType.VOID,
        statement.parameters.map { ParamSign(it.name, it.valueType) },
        statement.position!!.start
    )
}

fun generateConstructorFunctionSign(constr: ConstructorDefinitionStatement): FunctionSign {
    val functionName = constr.className + (constr.constructorName.let { ".$it" })
    return FunctionSign(
        functionName,
        VariableValueType(constr.className),
        constr.parameters.map { ParamSign(it.name, it.valueType) },
        constr.position!!.start
    )
}

fun generateClassSign(context: ScopeContext, statement: ClassDefinitionStatement): ClassSign {
    val classProperties = statement.properties.map {
        VariableSign(
            it.name,
            it.valueType ?: extractType(context, it.value!!),
            true,
            it.position!!.start
        )
    }

    val classMethods = statement.methods.map { generateFunctionSign(it) }

    return ClassSign(
        statement.name,
        classProperties,
        classMethods,
        emptyMap(),
        statement.position!!.start
    )
}

fun extractType(context: ScopeContext, e: Expression): VariableValueType =
    when (e) {
        is IntLiteralExpression -> VariableValueType.INT
        is DoubleLiteralExpression -> VariableValueType.DOUBLE
        is BooleanLitExpression -> VariableValueType.BOOLEAN
        is StringLiteralExpression -> VariableValueType.STRING
        is UnaryLogicNegationExpression -> VariableValueType.BOOLEAN
        is BinaryLogicExpression -> VariableValueType.BOOLEAN
        is BinaryComparisonExpression -> VariableValueType.BOOLEAN
        is BinaryExpression -> extractType(context, e.left)
        is UnaryMathExpression -> extractType(context, e.value)
        is ParenthesisExpression -> extractType(context, e.value)
        is VarReferenceExpression -> context.read<VariableSign>(e.name)?.type
            ?: VariableValueType.DYNAMIC
        is FunctionCallExpression -> extractTypeFromFunctionOrClassConstructor(e, context)
        is ObjectMethodCallExpression -> extractTypeFromClassMethod(e, context)
        is ObjectPropertyReferenceExpression -> extractTypeFromObjectProperty(e, context)
        else -> throw UnsupportedOperationException("Unknown expression type")
    }

fun extractTypeFromFunctionOrClassConstructor(
    e: FunctionCallExpression,
    context: ScopeContext
): VariableValueType {
    val classFound = context.read<ClassSign>(e.name)
    val functionFound = context.read<FunctionSign>(e.name)

    return when {
        classFound != null -> VariableValueType(e.name)
        functionFound != null -> functionFound.returnType
        else -> throw UnsupportedOperationException("Unknown function or class constructor")
    }
}

fun getClassFromObject(context: ScopeContext, objectName: String): ClassSign {
    val objectVariable = context.read<VariableSign>(objectName)
        ?: throw Exception("$objectName is not declared yet")

    return getClassFromContext(context, objectVariable.type.name)
}

fun getClassFromContext(context: ScopeContext, className: String): ClassSign {
    return context.read<ClassSign>(className)
        ?: throw Exception("$className class is not defined")
}

fun extractTypeFromClassMethod(
    e: ObjectMethodCallExpression,
    context: ScopeContext
): VariableValueType {
    val classSign = getClassFromObject(context, e.objectName)

    val methodSign = classSign.methods.firstOrNull { it.name == e.methodName }
        ?: throw Exception("${e.methodName} method is not defined")

    return methodSign.returnType
}

fun extractTypeFromObjectProperty(
    e: ObjectPropertyReferenceExpression,
    context: ScopeContext
): VariableValueType {
    val classSign = getClassFromObject(context, e.objectName)

    val propertySign = classSign.properties.firstOrNull { it.name == e.propertyName }
        ?: throw Exception("${e.propertyName} property is not defined")

    return propertySign.type
}
