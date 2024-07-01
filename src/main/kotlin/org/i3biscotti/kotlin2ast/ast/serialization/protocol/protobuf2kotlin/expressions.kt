package org.i3biscotti.kotlin2ast.ast.serialization.protocol.protobuf2kotlin

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.Expressions

fun Expressions.Expression.toAst(): Expression {
    if (hasIntLit()) {
        return intLit.toAst()
    } else if (hasStringLit()) {
        return stringLit.toAst()
    } else if (hasDecLit()) {
        return decLit.toAst()
    } else if (hasBoolLit()) {
        return boolLit.toAst()
    } else if (hasVarReferenceExpression()) {
        return varReferenceExpression.toAst()
    } else if (hasFunctionCallExpression()) {
        return functionCallExpression.toAst()
    } else if (hasBinaryMathExpression()) {
        return binaryMathExpression.toAst()
    } else if (hasUnaryMathExpression()) {
        return unaryMathExpression.toAst()
    } else if (hasBinaryLogicExpression()) {
        return binaryLogicExpression.toAst()
    } else if (hasUnaryLogicExpression()) {
        return binaryLogicExpression.toAst()
    } else if (hasBinaryComparisonExpression()) {
        return binaryComparisonExpression.toAst()
    } else if (hasInputExpression()) {
        return inputExpression.toAst()
    } else if (hasOutputExpression()) {
        return outputExpression.toAst()
    } else if (hasListLiteralExpression()) {
        return listLiteralExpression.toAst()
    } else if (hasParenthesisExpression()) {
        parenthesisExpression.toAst()
    } else if (hasObjectMethodCallExpression()) {
        return objectMethodCallExpression.toAst()
    } else if (hasObjectPropertyReferenceExpression()) {
        return objectPropertyReferenceExpression.toAst()
    } else if (hasInputExpression()){
        return inputExpression.toAst()
    } else if (hasOutputExpression()){
        return outputExpression.toAst()
    }

    throw IllegalArgumentException("Unknown expression type")
}

fun Expressions.IntLit.toAst(): IntLiteralExpression {
    return IntLiteralExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.StringLit.toAst(): StringLiteralExpression {
    return StringLiteralExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.DecLit.toAst(): DoubleLiteralExpression {
    return DoubleLiteralExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.BoolLit.toAst(): BooleanLitExpression {
    return BooleanLitExpression(
        value = value,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.VarReferenceExpression.toAst(): VarReferenceExpression {
    return VarReferenceExpression(
        name = name,
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.FunctionCallExpression.toAst(): FunctionCallExpression {
    return FunctionCallExpression(
        name = name,
        parameters = parametersList.map { it.toAst() },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.BinaryMathExpression.toAst(): BinaryMathExpression {
    return BinaryMathExpression(
        left = left.toAst(),
        right = right.toAst(),
        operand = when(operand){
            Expressions.MathOperand.PLUS -> MathOperand.plus
            Expressions.MathOperand.MINUS -> MathOperand.minus
            Expressions.MathOperand.TIMES -> MathOperand.times
            Expressions.MathOperand.DIVISION -> MathOperand.division
            else -> throw UnsupportedOperationException()

        },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.UnaryMathExpression.toAst(): UnaryMathExpression {
    return UnaryMathExpression(
        value = value.toAst(),
        operand = when(operand) {
            Expressions.MathOperand.MINUS -> MathOperand.minus
            else -> throw UnsupportedOperationException()
        },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.BinaryLogicExpression.toAst(): BinaryLogicExpression {
    return BinaryLogicExpression(
        left = left.toAst(),
        right = right.toAst(),
        operand = when (operand) {
            Expressions.LogicOperand.AND -> LogicOperand.and
            Expressions.LogicOperand.OR -> LogicOperand.or
            else -> throw UnsupportedOperationException()
        },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.UnaryLogicExpression.toAst(): UnaryLogicNegationExpression {
    if(operand == Expressions.LogicOperand.NOT) {
        return UnaryLogicNegationExpression(
            value = value.toAst(),
            position = if (hasPosition()) position.toAst() else null
        )
    }else {
        throw  UnsupportedOperationException()
    }
}

fun Expressions.BinaryComparisonExpression.toAst(): BinaryComparisonExpression {
    return BinaryComparisonExpression(
        left = left.toAst(),
        right = right.toAst(),
        operand = when (operand) {
            Expressions.ComparisonOperand.EQUAL -> ComparisonOperand.equal
            Expressions.ComparisonOperand.NOT_EQUAL -> ComparisonOperand.notEqual
            Expressions.ComparisonOperand.GREATER_THAN -> ComparisonOperand.greaterThan
            Expressions.ComparisonOperand.GREATER_THAN_OR_EQUAL -> ComparisonOperand.greaterThanOrEqual
            Expressions.ComparisonOperand.LESS_THAN -> ComparisonOperand.lessThan
            Expressions.ComparisonOperand.LESS_THAN_OR_EQUAL -> ComparisonOperand.lessThanOrEqual
            else -> throw UnsupportedOperationException()
        },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.InputExpression.toAst(): InputExpression {
    return InputExpression(
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.OutputExpression.toAst(): OutputExpression {
    return OutputExpression(
        value = value.toAst(),
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.ListLiteralExpression.toAst(): ListOfExpression {
    return ListOfExpression(
        items = valueList.map { it.toAst() },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.ParenthesisExpression.toAst(): ParenthesisExpression {
    return ParenthesisExpression(
        value = value.toAst(),
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.ObjectMethodCallExpression.toAst(): ObjectMethodCallExpression {
    return ObjectMethodCallExpression(
        objectName = objectName,
        methodName = methodName,
        params = parametersList.map { it.toAst() },
        position = if (hasPosition()) position.toAst() else null
    )
}

fun Expressions.ObjectPropertyReferenceExpression.toAst(): ObjectPropertyReferenceExpression {
    return ObjectPropertyReferenceExpression(
        objectName = objectName,
        propertyName = propertyName,
        position = if (hasPosition()) position.toAst() else null
    )
}
